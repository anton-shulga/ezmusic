package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;

/**
 * Created by Антон on 16.07.2016.
 */
public interface UserDAO extends AbstractDAO<User, Long> {

    User login(String userLogin, String userPassword) throws DAOException;
     boolean isLoginExist(String login) throws DAOException;

}
