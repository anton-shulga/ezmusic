package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.order.FindCartByUserIdService;
import by.epam.webpoject.ezmusic.service.user.LoginUserService;
import by.epam.webpoject.ezmusic.validator.UserParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 15.07.2016.
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        User user = null;

        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);

        boolean isValidRequest = UserParametersValidator.validateLoginParameters(login, password);
        if(isValidRequest) {
            try {
                user = LoginUserService.execute(login, password);

                if(user != null) {
                    if(user.getIsAdmin()){
                        request.getSession(true).setAttribute(RequestParameter.USER, user);
                        page = JspPageName.ADMIN_HOME;
                    }else {
                        Order cart = FindCartByUserIdService.find(user.getUserId());
                        request.getSession().setAttribute(RequestParameter.CART, cart);
                        request.getSession().setAttribute(RequestParameter.USER, user);
                        page = JspPageName.USER_HOME;
                    }
                }else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.AUTH_ERROR);
                    page = JspPageName.LOGIN;
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.LOGIN;
        }

        return page;

    }
}
