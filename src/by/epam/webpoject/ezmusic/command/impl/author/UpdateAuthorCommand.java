package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.author.UpdateAuthorService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class UpdateAuthorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Author author = null;

        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);
        String[] albumIds =request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] songIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String name = request.getParameter(RequestParameter.AUTHOR_NAME);
        String country = request.getParameter(RequestParameter.AUTHOR_COUNTRY);
        String photoPath = request.getParameter(RequestParameter.AUTHOR_PHOTO_PATH);

        boolean isValidRequest = AuthorParametersValidator.validateUpdateParameters(authorId, albumIds, songIds, name, country, photoPath);
        if(isValidRequest) {
            author = new Author();
            author.setAuthorId(ParameterParser.parseLong(authorId));
            author.setName(name);
            author.setCountry(country);
            author.setPhotoPath(photoPath);

            try {
                UpdateAuthorService.update(author, ParameterParser.parseLongArray(albumIds), ParameterParser.parseLongArray(songIds));

                ArrayList<Author> authorList = FindAllAuthorsService.find();
                request.setAttribute(RequestParameter.MESSAGE, "Successfully updated author");
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                page = JspPageName.ADMIN_ALL_AUTHORS;
            } catch (ServiceException e) {
                throw new CommandException("Update author command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
            page = JspPageName.ADMIN_HOME;
        }

        return page;
    }
}
