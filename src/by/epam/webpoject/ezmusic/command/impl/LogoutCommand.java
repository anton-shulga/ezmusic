package by.epam.webpoject.ezmusic.command.impl;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.exception.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 12.08.2016.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return JspPageName.INDEX;
    }
}
