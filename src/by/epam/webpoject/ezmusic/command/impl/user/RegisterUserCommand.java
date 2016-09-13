package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.user.RegisterUserService;
import by.epam.webpoject.ezmusic.validator.UserParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 25.07.2016.
 */
public class RegisterUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Long generatedId = null;
        User user = null;

        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        String firstName = request.getParameter(RequestParameter.USER_FIRST_NAME);
        String surname = request.getParameter(RequestParameter.USER_SURNAME);
        String email = request.getParameter(RequestParameter.USER_EMAIL);
        String phone = request.getParameter(RequestParameter.USER_PHONE);

        boolean isValidRequest = UserParametersValidator.validateRegisterParameters(login, password, firstName, surname, email, phone);
        if (isValidRequest) {
            user = new User();
            user.setName(firstName);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);

            try {
                generatedId = RegisterUserService.register(user);
                if (generatedId != null) {
                    request.setAttribute(RequestParameter.MESSAGE, "Registration completed successfully");
                    page = JspPageName.LOGIN;
                } else {
                    request.setAttribute(RequestParameter.MESSAGE, "Login is already exist");
                    page = JspPageName.REGISTER;
                }
            } catch (ServiceException e) {
                throw new CommandException("Register user command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
            page = JspPageName.REGISTER;
        }

        return page;

    }
}
