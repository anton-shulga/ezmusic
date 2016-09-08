package by.epam.webpoject.ezmusic.service.user;

import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 02.08.2016.
 */
public class CheckLoginAvailabilityService {
    public static boolean isLoginExist(String login) throws ServiceException {
        UserDAO userDAO = (UserDAO) DAOFactory.createUserDAO();
        try {
            return userDAO.isLoginExist(login);
        } catch (DAOException e) {
            throw new ServiceException("Check login availability service exception", e);
        }
    }
}
