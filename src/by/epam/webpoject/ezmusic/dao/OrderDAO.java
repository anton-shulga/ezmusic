package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 21.08.2016.
 */
public interface OrderDAO extends AbstractDAO<Order, Long> {
    ArrayList<Order> findByUserId(Long userId) throws DAOException;

    Order findCartByUserId(Long userId) throws DAOException;

}
