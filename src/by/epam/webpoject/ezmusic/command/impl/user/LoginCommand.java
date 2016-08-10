package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.user.LoginUserService;
import by.epam.webpoject.ezmusic.validator.LoginRequestValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 15.07.2016.
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user;
        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        try {
            boolean isValidRequest = LoginRequestValidator.validate(login, password);
            if(isValidRequest) {
                user = LoginUserService.execute(request.getParameter(RequestParameter.USER_LOGIN), request.getParameter(RequestParameter.USER_PASSWORD));
            }else return JspPageName.ERROR;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if(user != null) {
            request.getSession(true).setAttribute(RequestParameter.USER, user);
            return JspPageName.USER_HOME;
        }else {
            request.setAttribute(RequestParameter.MESSAGE, "The username or password you entered is incorrect");
            return JspPageName.INDEX;
        }

    }
}
