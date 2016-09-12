package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.author.CreateAuthorService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class CreateAuthorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Long generatedId = null;
        Author author = null;

        String[] albumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] songIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String name = request.getParameter(RequestParameter.AUTHOR_NAME);
        String country = request.getParameter(RequestParameter.AUTHOR_COUNTRY);
        String photoPath = request.getParameter(RequestParameter.AUTHOR_PHOTO_PATH);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);
        try {
            if (!f5Pressed(sessionToken, requestToken)) {
                request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
                boolean isValidRequest = AuthorParametersValidator.validateCreateParameters(albumIds, songIds, name, country, photoPath);
                if (isValidRequest) {
                    author = new Author();
                    author.setName(name);
                    author.setCountry(country);
                    author.setPhotoPath(photoPath);

                    generatedId = CreateAuthorService.create(author, ParameterParser.parseLongArray(albumIds), ParameterParser.parseLongArray(songIds));

                    if (generatedId != null) {
                        ArrayList<Author> allAuthors = FindAllAuthorsService.find();
                        request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                        request.setAttribute(RequestParameter.MESSAGE, "Successfully created author " + name);
                        page = JspPageName.ADMIN_ALL_AUTHORS;
                    } else {
                        request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                        page = JspPageName.ADMIN_HOME;
                    }

                } else {
                    request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check input parameters");
                    page = JspPageName.ADMIN_HOME;
                }
            }else {
                ArrayList<Author> allAuthors = FindAllAuthorsService.find();
                request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                page = JspPageName.ADMIN_ALL_AUTHORS;
            }
        } catch (ServiceException e) {
            throw new CommandException("Create author command exception", e);
        }

        return page;

    }

    private boolean f5Pressed(String sessionToken, String requestToken){
        if(sessionToken != null){
            if(requestToken != null){
                return sessionToken.equals(requestToken);
            }
        }else {
            return false;
        }
        return false;
    }
}
