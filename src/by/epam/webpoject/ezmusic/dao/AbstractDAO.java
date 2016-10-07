package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Антон on 16.07.2016.
 */

/**
 * Interface for a Data Access Object that can be used for a single specified type entity object.
 * A single instance implementing this interface can be used only for the type of bean object specified in the type
 * parameters.
 * Define CRUD operations
 *
 * @param <T> The type of the entity object for which this instance is to be used.
 * @param <K> The type of the transfer object
 */
public interface AbstractDAO<T, K> {
    Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    /**
     * Add entity to database
     *
     * @param instance Entity to add
     * @return transfer object for entity
     * @throws DAOException if database error was detected
     */
    K create(T instance) throws DAOException;

    /**
     * Find entity by specified id in database
     *
     * @param id specified id of entity
     * @return special type of entity
     * @throws DAOException if database error was detected
     */
    T find(K id) throws DAOException;

    /**
     * Update entity in database
     *
     * @param instance entity to update
     * @throws DAOException if database error was detected
     */
    void update(T instance) throws DAOException;

    /**
     * Delete entity from database
     *
     * @param id specified id of entity
     * @throws DAOException if database error was detected
     */
    void delete(K id) throws DAOException;

    /**
     * Close statement
     *
     * @param statement Statement object to close
     */
    default void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
