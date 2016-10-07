package by.epam.webpoject.ezmusic.service.user;

import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.util.MD5Encryptor;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 20.07.2016.
 */
public class LoginUserService {

    public static User execute(String username, String password) throws ServiceException {

        UserDAO userDAO = (UserDAO) DAOFactory.createUserDAO();

        try {
            String md5Hash = MD5Encryptor.getMD5(password);
            return userDAO.login(username, md5Hash);
        } catch (DAOException e) {
            throw new ServiceException("Login user service exception", e);
        }

    }
}
