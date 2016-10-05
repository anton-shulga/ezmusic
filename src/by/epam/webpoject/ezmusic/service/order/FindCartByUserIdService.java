package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.song.FindSongsByOrderIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 01.09.2016.
 */
public class FindCartByUserIdService {
    public static Order find(Long userId) throws ServiceException {

        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();

        try {
            Order cart = orderDAO.findCartByUserId(userId);

            if (cart == null) {
                cart = new Order();
                cart.setPaid(false);
                cart.setUserId(userId);
                cart.setOrderId(orderDAO.create(cart));
                cart.setSongList(new ArrayList<Song>());
            }

            ArrayList<Song> songList = FindSongsByOrderIdService.find(cart.getOrderId());

            cart.setSongList(songList);

            double totalCost = 0;
            for (Song song : cart.getSongList()) {
                totalCost += song.getCost();
            }
            cart.setTotalCost(totalCost);

            return cart;
        } catch (DAOException e) {
            throw new ServiceException("Find cart by user id service exception", e);
        }
    }
}
