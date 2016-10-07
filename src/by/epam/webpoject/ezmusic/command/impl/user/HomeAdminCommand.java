package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 29.08.2016.
 */
public class HomeAdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ADMIN_HOME;
    }
}
