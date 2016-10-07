package by.epam.webpoject.ezmusic.command;

import by.epam.webpoject.ezmusic.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 15.07.2016.
 */

/**
 * Realization of command pattern
 */
public interface Command {
    /**
     * @param request object that contains the request the client has made of the servlet
     * @return string that contains link to page, on which user will be redirected
     * @throws CommandException if service exception is detected
     */
    String execute(HttpServletRequest request) throws CommandException;
}
