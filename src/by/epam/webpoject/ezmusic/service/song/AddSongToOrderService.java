package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 30.08.2016.
 */
public class AddSongToOrderService {
    public static boolean add(Long userId, Long songId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        try {
            Order unpaidOrder = orderDAO.findCartByUserId(userId);
            if (unpaidOrder == null) {
                unpaidOrder = new Order();
                unpaidOrder.setPaid(false);
                unpaidOrder.setUserId(userId);
                unpaidOrder.setOrderId(orderDAO.create(unpaidOrder));
            }
            return songDAO.createSongOrder(songId, unpaidOrder.getOrderId());
        } catch (DAOException e) {
            throw new ServiceException("Add song to order service exception");
        }
    }
}
