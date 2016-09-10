package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
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
        ArrayList<Author> authorList = null;

        try {
            authorList = FindAllAuthorsService.find();

            if(authorList != null){
                if(authorList.isEmpty()){
                    request.setAttribute(RequestParameter.MESSAGE, "Not found any authors");
                }
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                page = JspPageName.ADMIN_ALL_AUTHORS;
            }else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find all authors command exception", e);
        }
        return page;
    }
}
