package by.epam.webpoject.ezmusic.service;

import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 25.07.2016.
 */
public class DeleteUserService {
    public static boolean execute(Long id) throws ServiceException {
        UserDAO dao = (UserDAO) DAOFactory.createUserDao();
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
