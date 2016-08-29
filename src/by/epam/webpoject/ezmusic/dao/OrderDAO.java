package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 21.08.2016.
 */
public abstract class OrderDAO implements AbstractDAO<Order, Long> {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

    public abstract ArrayList<Order> findByUserId(Long userId) throws DAOException;
    public abstract Order findCartByUserId(Long userId);
    @Override
    public void closeStatement(Statement statement) {
        try {
            if(statement != null)
                statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
