package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.*;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.CreateAlbumService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Long generatedId = null;
        Album album = null;

        String[] selectedSongIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String name = request.getParameter(RequestParameter.ALBUM_NAME);
        String year = request.getParameter(RequestParameter.ALBUM_YEAR);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);

        try {
            if (!f5Pressed(sessionToken, requestToken)) {
                request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
                boolean isValidRequest = AlbumParametersValidator.validateCreateParameters(selectedSongIds, authorIds, name, year);
                if (isValidRequest) {
                    album = new Album();
                    album.setName(name);
                    album.setYear(ParameterParser.parseInt(year));
                    try {
                        if (request.getPart(RequestParameter.ALBUM_IMAGE_FILE_PATH).getInputStream().available() != 0) {
                            String imagePath = loadImage(request);
                            if (imagePath != null) {
                                album.setImageFilePath(imagePath);
                            } else {
                                album.setImageFilePath(FilePath.DEFAULT_ALBUM_PHOTO);
                            }
                        }
                    } catch (IOException | ServletException e) {
                        album.setImageFilePath(FilePath.DEFAULT_ALBUM_PHOTO);
                    }

                    generatedId = CreateAlbumService.create(album, ParameterParser.parseLongArray(selectedSongIds), ParameterParser.parseLongArray(authorIds));

                    if (generatedId != null) {
                        ArrayList<Album> albumList = FindAllAlbumsService.find();
                        request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                        request.setAttribute(RequestParameter.MESSAGE, MessageKey.CREATED);
                        page = JspPageName.ADMIN_ALL_ALBUMS;
                    } else {
                        request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                        page = JspPageName.ADMIN_EDIT_ALBUM;
                    }

                } else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
                    page = JspPageName.ADMIN_EDIT_ALBUM;
                }
            } else {
                ArrayList<Album> albumList = FindAllAlbumsService.find();
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                page = JspPageName.ADMIN_ALL_ALBUMS;
            }
        } catch (ServiceException e) {
            throw new CommandException("Create album command exception", e);
        }

        return page;
    }

    private String loadImage(HttpServletRequest request) throws CommandException {
        String appPath = request.getServletContext().getRealPath(File.separator);
        String imageDirectoryName = request.getServletContext().getInitParameter(ContextParameter.ALBUM_IMAGE_DIRECTORY);
        String filePath = appPath + File.separator + imageDirectoryName;

        try {
            if (!Files.exists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }
        } catch (IOException e) {
            throw new CommandException("Can't create directory for album image", e);
        }

        String imageName = Double.toString(new Date().getTime()) + FileExtension.JPG;
        File file = new File(filePath + File.separator + imageName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new CommandException("Can't load image", e);
        }

        try (
                InputStream inputStream = request.getPart(RequestParameter.ALBUM_IMAGE_FILE_PATH).getInputStream();
                FileOutputStream outputStream = new FileOutputStream(file)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (ServletException | IOException e) {
            throw new CommandException("Can't copy image", e);
        }

        return imageDirectoryName + File.separator + imageName;

    }

    private boolean f5Pressed(String sessionToken, String requestToken) {
        if (sessionToken != null) {
            if (requestToken != null) {
                return sessionToken.equals(requestToken);
            }
        } else {
            return false;
        }
        return false;
    }
}
