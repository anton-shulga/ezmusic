package by.epam.webpoject.ezmusic.command.impl;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.exception.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 09.08.2016.
 */
public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String uri = request.getHeader("referer");
        String pageName = uri.substring(uri.lastIndexOf("/")+1);
        return pageName;
    }
}
