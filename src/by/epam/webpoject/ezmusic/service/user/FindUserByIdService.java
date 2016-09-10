package by.epam.webpoject.ezmusic.service.user;

import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 25.07.2016.
 */
public class FindUserByIdService {
    public static User find(Long id) throws ServiceException {

        UserDAO dao = (UserDAO) DAOFactory.createUserDAO();

        try {
            return dao.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Find user by id service exception", e);
        }
    }
}
