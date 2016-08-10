package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.user.CheckLoginAvailabilityService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 02.08.2016.
 */
public class CheckLoginAvailabilityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameter.USER_LOGIN);
        try {
            boolean isLoginExist = CheckLoginAvailabilityService.isLoginExist(login);
            if(isLoginExist) {
                return "Login " + login + " is already exist.";
            }else {
                return "Login " + login + " is available.";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
