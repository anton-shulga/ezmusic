package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 30.08.2016.
 */
public class AddSongToOrderService {
    public static boolean add(Long songId, Order cart, Long userId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        try {
            if (cart == null) {
                cart = new Order();
                cart.setPaid(false);
                cart.setUserId(userId);
                cart.setOrderId(orderDAO.create(cart));
                cart.setSongList(new ArrayList<Song>());
            }
            Song song = songDAO.find(songId);
            cart.getSongList().add(song);
            return songDAO.createSongOrder(songId, cart.getOrderId());
        } catch (DAOException e) {
            throw new ServiceException("Add song to order service exception");
        }
    }
}
