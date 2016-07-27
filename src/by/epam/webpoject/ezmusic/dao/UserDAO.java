package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

/**
 * Created by Антон on 16.07.2016.
 */
public abstract class UserDAO implements AbstractDAO<User, Long> {
    public abstract User login(String userLogin, String userPassword) throws DAOException;
}
