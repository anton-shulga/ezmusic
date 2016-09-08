package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.order.FindOrdersByUserIdService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 07.09.2016.
 */
public class FindOrdersUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        ArrayList<Order> orderList = null;
        try {
            orderList = FindOrdersByUserIdService.find(user.getUserId());
            request.setAttribute(RequestParameter.ORDERS, orderList);
            page = JspPageName.USER_ORDERS;
        } catch (ServiceException e) {
            throw new CommandException("Find orders user command exception", e);
        }
        return page;
    }
}
