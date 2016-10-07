package by.epam.webpoject.ezmusic.command.impl;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Антон on 12.08.2016.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(RequestParameter.USER) != null) {
            session.invalidate();
            page = JspPageName.INDEX;
        } else {
            page = JspPageName.INDEX;
        }
        return page;
    }
}
