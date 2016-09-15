package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Антон on 16.07.2016.
 */
public interface AbstractDAO<T, K> {
    Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    K create(T instance) throws DAOException;

    T find(K id) throws DAOException;

    void update(T instance) throws DAOException;

    void delete(K id) throws DAOException;

    default void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
