package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;

/**
 * Created by Антон on 16.07.2016.
 */

/**
 * Interface for for a Data Access Object that uses for User entity {@link User}
 */
public interface UserDAO extends AbstractDAO<User, Long> {

    /**
     * Check specified login and password in the database.
     *
     * @param userLogin    specified user login
     * @param userPassword specified user password
     * @return user entity if user with specified login and password exist, otherwise return null
     * @throws DAOException if database error was detected
     */
    User login(String userLogin, String userPassword) throws DAOException;

    /**
     * Check specified login in the database.
     *
     * @param login specified user login
     * @return true if login is already exist, otherwise return false
     * @throws DAOException if database error was detected
     */
    boolean isLoginExist(String login) throws DAOException;

}
