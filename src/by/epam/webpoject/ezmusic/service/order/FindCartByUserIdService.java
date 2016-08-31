package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 01.09.2016.
 */
public class FindCartByUserIdService {
    public static Order find(Long userId) throws ServiceException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        try {
            Order cart = orderDAO.findCartByUserId(userId);
            cart.setSongList(songDAO.findByOrderId(cart.getOrderId()));
            return cart;
        } catch (DAOException e) {
            throw new ServiceException("Find cart service exception", e);
        }
    }
}
