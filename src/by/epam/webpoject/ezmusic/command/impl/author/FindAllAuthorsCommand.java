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
 * Created by Антон on 19.08.2016.
 */
public class FindAllAuthorsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Author> allAuthors = null;
        try {
            allAuthors = FindAllAuthorsService.find();
        } catch (ServiceException e) {
            throw new CommandException("Finding authors error", e);
        }
        if(allAuthors != null){
            request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
            page = JspPageName.ADMIN_ALL_AUTHORS;
        }else {
            page = JspPageName.ERROR;
        }
        return page;
    }
}
