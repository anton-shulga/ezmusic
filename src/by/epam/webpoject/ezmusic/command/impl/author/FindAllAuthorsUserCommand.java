package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 30.08.2016.
 */
public class FindAllAuthorsUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Author> allAuthors = null;
        try {
            allAuthors = FindAllAuthorsService.find();
            if(allAuthors != null){
                request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                page = JspPageName.USER_ALL_AUTHORS;
            }else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.USER_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find authors command exception", e);
        }
        return page;
    }
}
