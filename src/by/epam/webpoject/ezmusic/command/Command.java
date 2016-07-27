package by.epam.webpoject.ezmusic.command;

import by.epam.webpoject.ezmusic.exception.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 15.07.2016.
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
