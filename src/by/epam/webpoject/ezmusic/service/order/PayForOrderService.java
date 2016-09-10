package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.DAOException;

/**
 * Created by Антон on 04.09.2016.
 */
public class PayForOrderService {
    public static boolean pay(User user, Order cart) throws CommandException {

        UserDAO userDAO = (UserDAO) DAOFactory.createUserDAO();
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();

        double userBalance = user.getBalance();
        double cartTotalCost = cart.getTotalCost();

        if(userBalance < cartTotalCost){
            return false;
        }else {
            user.setBalance(userBalance-cartTotalCost);
            cart.setPaid(true);
            try {
                userDAO.update(user);
                orderDAO.update(cart);
                return true;
            } catch (DAOException e) {
                throw new CommandException("Pay for order command exception", e);
            }
        }
    }
}
