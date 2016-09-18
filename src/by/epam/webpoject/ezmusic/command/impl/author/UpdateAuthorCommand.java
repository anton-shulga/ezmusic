package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.*;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.author.UpdateAuthorService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

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
 * Created by Антон on 19.08.2016.
 */
public class UpdateAuthorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Author author = null;

        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);
        String[] albumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] songIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String name = request.getParameter(RequestParameter.AUTHOR_NAME);
        String country = request.getParameter(RequestParameter.AUTHOR_COUNTRY);

        boolean isValidRequest = AuthorParametersValidator.validateUpdateParameters(authorId, albumIds, songIds, name, country);
        if (isValidRequest) {
            author = new Author();
            author.setAuthorId(ParameterParser.parseLong(authorId));
            author.setName(name);
            author.setCountry(country);

            try {
                if (request.getPart(RequestParameter.AUTHOR_PHOTO_PATH).getInputStream().available() != 0) {
                    String imagePath = loadImage(request);
                    if (imagePath != null) {
                        author.setPhotoPath(imagePath);
                    }
                } else {
                    author.setPhotoPath(request.getParameter(RequestParameter.OLD_AUTHOR_PHOTO_PATH));
                }
            } catch (IOException | ServletException e) {
                author.setPhotoPath(request.getParameter(RequestParameter.OLD_AUTHOR_PHOTO_PATH));
            }

            try {
                UpdateAuthorService.update(author, ParameterParser.parseLongArray(albumIds), ParameterParser.parseLongArray(songIds));

                ArrayList<Author> authorList = FindAllAuthorsService.find();
                request.setAttribute(RequestParameter.MESSAGE, "Successfully updated author");
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                page = JspPageName.ADMIN_ALL_AUTHORS;
            } catch (ServiceException e) {
                throw new CommandException("Update author command exception", e);
            }
        } else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
            page = JspPageName.ADMIN_EDIT_AUTHOR;
        }

        return page;
    }

    private String loadImage(HttpServletRequest request) throws CommandException {
        String appPath = request.getServletContext().getRealPath(File.separator);
        String imageDirectoryName = request.getServletContext().getInitParameter(ContextParameter.AUTHOR_IMAGE_DIRECTORY);
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
                InputStream inputStream = request.getPart(RequestParameter.AUTHOR_PHOTO_PATH).getInputStream();
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
