package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Антон on 21.08.2016.
 */
public abstract class OrderDAO implements AbstractDAO<Order, Long> {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

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
