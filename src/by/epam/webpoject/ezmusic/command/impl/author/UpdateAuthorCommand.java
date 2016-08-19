package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.author.UpdateAuthorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class UpdateAuthorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Long authorId = ParameterParser.parseLong(request.getParameter(RequestParameter.AUTHOR_ID));
        Long[] albumIds = ParameterParser.parseLongArray(request.getParameterValues(RequestParameter.SELECTED_ALBUMS));
        Long[] songIds = ParameterParser.parseLongArray(request.getParameterValues(RequestParameter.SELECTED_SONGS));
        String name = request.getParameter(RequestParameter.AUTHOR_NAME);
        String country = request.getParameter(RequestParameter.AUTHOR_COUNTRY);
        String photoPath = request.getParameter(RequestParameter.AUTHOR_PHOTO_PATH);
        Author author = new Author();
        author.setAuthorId(authorId);
        author.setName(name);
        author.setCountry(country);
        author.setPhotoPath(photoPath);
        try {
            UpdateAuthorService.update(author, albumIds, songIds);
            ArrayList<Author> allAuthors = FindAllAuthorsService.find();
            request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
            page = JspPageName.ADMIN_ALL_AUTHORS;

        } catch (ServiceException e) {
            throw new CommandException("Creating author error", e);
        }
        return page;

    }
}
