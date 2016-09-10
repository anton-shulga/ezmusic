package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.song.FindSongsByOrderIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 07.09.2016.
 */
public class FindAllOrdersByUserIdService {
    public static ArrayList<Order> find(Long userId) throws ServiceException {

        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();

        ArrayList<Order> orderList = null;
        try {
            orderList = orderDAO.findByUserId(userId);
            for(Order order : orderList){
                order.setSongList(FindSongsByOrderIdService.find(order.getOrderId()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Find all orders by user id service exception", e);
        }
        return orderList;
    }
}
