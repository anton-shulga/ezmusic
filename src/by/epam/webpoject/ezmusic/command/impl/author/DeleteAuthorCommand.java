package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.author.DeleteAuthorService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class DeleteAuthorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;

        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);

        boolean isValidRequest = AuthorParametersValidator.validateDeleteParameters(authorId);
        if (isValidRequest) {
            try {
                DeleteAuthorService.delete(ParameterParser.parseLong(authorId));
                ArrayList<Author> allAuthors = FindAllAuthorsService.find();
                request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.DELETED);
                page = JspPageName.ADMIN_ALL_AUTHORS;
            } catch (ServiceException e) {
                throw new CommandException("Delete author command exception", e);
            }
        } else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
