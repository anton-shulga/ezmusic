package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 21.08.2016.
 */
/**
 * Interface for for a Data Access Object that uses for Order entity {@link Order}
 */
public interface OrderDAO extends AbstractDAO<Order, Long> {
    /**
     * Find order by specified user id
     *
     * @param userId specified user id
     * @return list of orders, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Order> findByUserId(Long userId) throws DAOException;

    /**
     * Find unpaid order by specified user id
     *
     * @param userId specified user id
     * @return unpaid order entity
     * @throws DAOException if database error was detected
     */
    Order findCartByUserId(Long userId) throws DAOException;

}
