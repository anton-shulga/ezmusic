package by.epam.webpoject.ezmusic.service.user;

import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 25.07.2016.
 */
public class DeleteUserService {
    public static void delete(Long id) throws ServiceException {
        UserDAO dao = (UserDAO) DAOFactory.createUserDAO();
        try {
            dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Delete user service exception", e);
        }
    }
}
