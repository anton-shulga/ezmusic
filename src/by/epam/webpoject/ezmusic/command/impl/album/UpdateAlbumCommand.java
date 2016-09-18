package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.*;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.album.UpdateAlbumService;
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
public class UpdateAlbumCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Album album = null;

        String[] songIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String albumId = request.getParameter(RequestParameter.ALBUM_ID);
        String name = request.getParameter(RequestParameter.ALBUM_NAME);
        String year = request.getParameter(RequestParameter.ALBUM_YEAR);

        boolean isValidRequest = AlbumParametersValidator.validateUpdateParameters(albumId, songIds, authorIds, name, year);
        if (isValidRequest) {
            album = new Album();
            album.setAlbumId(ParameterParser.parseLong(albumId));
            album.setName(name);
            album.setYear(ParameterParser.parseInt(year));

            try {
                if (request.getPart(RequestParameter.ALBUM_IMAGE_FILE_PATH).getInputStream().available() != 0) {
                    String imagePath = loadImage(request);
                    if (imagePath != null) {
                        album.setImageFilePath(imagePath);
                    }
                } else {
                    album.setImageFilePath(request.getParameter(RequestParameter.OLD_ALBUM_IMAGE_FILE_PATH));
                }
            } catch (IOException | ServletException e) {
                album.setImageFilePath(request.getParameter(RequestParameter.OLD_ALBUM_IMAGE_FILE_PATH));
            }


            try {
                UpdateAlbumService.update(album, ParameterParser.parseLongArray(songIds), ParameterParser.parseLongArray(authorIds));
                ArrayList<Album> albumList = FindAllAlbumsService.find();
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                request.setAttribute(RequestParameter.MESSAGE, "Successfully updated album");
                page = JspPageName.ADMIN_ALL_ALBUMS;
            } catch (ServiceException e) {
                throw new CommandException("Update album command exception", e);
            }
        } else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data.");
            page = JspPageName.ADMIN_EDIT_ALBUM;
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

        String imageName = Double.toString(new Date().getTime()) + FileExtention.JPG;
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
}
