package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.service.order.PayForOrderService;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Антон on 02.09.2016.
 */
public class PayForOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;

        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        Order cart = (Order) request.getSession().getAttribute(RequestParameter.CART);

        boolean isPaid = PayForOrderService.pay(user, cart);
        if(isPaid){
            page = JspPageName.USER_HOME;
            request.getSession().removeAttribute(RequestParameter.CART);
        }else {
            request.setAttribute(RequestParameter.MESSAGE, "You do not have enough money. Please, add funds");
            return JspPageName.USER_CART;
        }
        return page;
    }
}
