package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;


import java.util.ArrayList;

/**
 * Created by Антон on 07.09.2016.
 */
public class FindOrdersByUserIdService {
    public static ArrayList<Order> find(Long userId) throws ServiceException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Order> orderList = null;
        try {
            orderList = orderDAO.findByUserId(userId);
            for(Order order : orderList){
                order.setSongList(songDAO.findByOrderId(order.getOrderId()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Find orders service exception", e);
        }
        return orderList;
    }
}
