package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.order.GetOrderSongsNumberByUserIdService;
import by.epam.webpoject.ezmusic.service.user.LoginUserService;
import by.epam.webpoject.ezmusic.validator.LoginRequestValidator;

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
        boolean isValidRequest = LoginRequestValidator.validate(login, password);
        if(isValidRequest) {
            try {
                user = LoginUserService.execute(login, password);
                if(user != null) {
                    if(user.getIsAdmin()){
                        request.getSession(true).setAttribute(RequestParameter.USER, user);
                        page = JspPageName.ADMIN_HOME;
                    }else {
                        Long orderSongsNumber = GetOrderSongsNumberByUserIdService.get(user.getUserId());
                        request.getSession().setAttribute(RequestParameter.ORDER_SONGS_NUMBER, orderSongsNumber);
                        request.getSession().setAttribute(RequestParameter.USER, user);
                        page = JspPageName.USER_HOME;
                    }
                }else {
                    request.setAttribute(RequestParameter.MESSAGE, "The username or password you entered is incorrect");
                    page = JspPageName.LOGIN;
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }else {
            page = JspPageName.LOGIN;
        }
        return page;

    }
}
