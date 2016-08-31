package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 30.08.2016.
 */
public class GetOrderSongsNumberByUserIdService {
    public static Long get(Long userId) throws ServiceException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        Order cart = null;
        try {
            cart = orderDAO.findCartByUserId(userId);
            return orderDAO.getOrderSongsNumber(cart.getOrderId());
        } catch (DAOException e) {
          throw new ServiceException("Get order songs number service exception", e);
        }
    }
}
