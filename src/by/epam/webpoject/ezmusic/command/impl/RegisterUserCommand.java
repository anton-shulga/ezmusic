package by.epam.webpoject.ezmusic.command.impl;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.RegisterUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 25.07.2016.
 */
public class RegisterUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long userId;
        try {
            User user = new User();
            user.setName(request.getParameter(RequestParameter.USER_NAME));
            user.setSurname(request.getParameter(RequestParameter.USER_SURNAME));
            user.setLogin(request.getParameter(RequestParameter.USER_LOGIN));
            user.setPassword(RequestParameter.USER_PASSWORD);
            user.setEmail(RequestParameter.USER_EMAIL);
            user.setPhone(RequestParameter.USER_PHONE);
            user.setBalance(Double.parseDouble(RequestParameter.USER_BALANCE));
            user.setAdmin(Boolean.parseBoolean(RequestParameter.USER_IS_ADMIN));
            user.setBanned(Boolean.parseBoolean(RequestParameter.USER_IS_BANNED));
            userId = RegisterUserService.execute(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if(userId != null) {
            return JspPageName.USER_HOME;
        }else {
            return JspPageName.INDEX;
        }
    }
}
