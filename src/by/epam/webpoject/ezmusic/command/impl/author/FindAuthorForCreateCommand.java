package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.exception.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAuthorForCreateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
    }
}
