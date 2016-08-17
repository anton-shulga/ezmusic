package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.user.RegisterUserService;
import by.epam.webpoject.ezmusic.validator.RegisterRequestValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 25.07.2016.
 */
public class RegisterUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long generatedId = null;
        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        String firstName = request.getParameter(RequestParameter.USER_FIRST_NAME);
        String surname = request.getParameter(RequestParameter.USER_SURNAME);
        String email = request.getParameter(RequestParameter.USER_EMAIL);
        String phone = request.getParameter(RequestParameter.USER_PHONE);

        boolean isValidRequest = RegisterRequestValidator.validate(login, password, firstName, surname, email, phone);

        if (isValidRequest) {
            User user = new User();
            user.setName(firstName);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            try {
                generatedId = RegisterUserService.register(user);
            } catch (ServiceException e) {
                throw new CommandException("Registration error", e);
            }
        }
        if (generatedId != null) {
            request.setAttribute(RequestParameter.MESSAGE, "Successfully registered!");
            return JspPageName.LOGIN;
        } else {
            request.setAttribute(RequestParameter.MESSAGE, "Login is already exist!");
            return JspPageName.REGISTER;
        }
    }
}
