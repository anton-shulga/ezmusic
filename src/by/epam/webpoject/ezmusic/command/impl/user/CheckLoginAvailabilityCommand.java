package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.user.CheckLoginAvailabilityService;
import by.epam.webpoject.ezmusic.validator.UserParametersValidator;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 02.08.2016.
 */
public class CheckLoginAvailabilityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String output = null;

        String login = request.getParameter(RequestParameter.USER_LOGIN);
        boolean isValidRequest = UserParametersValidator.validateCheckLoginAvailabilityParameters(login);

        if(isValidRequest) {
            try {
                boolean isLoginExist = CheckLoginAvailabilityService.isLoginExist(login);
                if (isLoginExist) {
                    output = "Login is already exist";//don't know how to fix it
                } else {
                    output = "Login is available";
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }

        return new Gson().toJson(output);
    }
}
