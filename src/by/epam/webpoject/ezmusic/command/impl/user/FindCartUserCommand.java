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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 01.09.2016.
 */
public class FindCartUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Order cart = null;

        User user = (User) request.getSession().getAttribute(RequestParameter.USER);

        try {
            cart = FindCartByUserIdService.find(user.getUserId());

            if (cart.getSongList().isEmpty()) {
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.CART_EMPTY);
            }
            request.getSession().setAttribute(RequestParameter.CART, cart);
            page = JspPageName.USER_CART;
        } catch (ServiceException e) {
            throw new CommandException("Find cart user command exception", e);
        }
        return page;
    }
}
