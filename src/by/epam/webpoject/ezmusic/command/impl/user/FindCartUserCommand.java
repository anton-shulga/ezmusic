package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.order.FindCartByUserIdService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 01.09.2016.
 */
public class FindCartUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        User user = (User) request.getSession().getAttribute(RequestParameter.USER);

        try {
            Order cart = FindCartByUserIdService.find(user.getUserId());
            request.setAttribute(RequestParameter.CART, cart);
            page = JspPageName.USER_CART;
        } catch (ServiceException e) {
            throw new CommandException("Find cart command exception", e);
        }
        return page;
    }
}
