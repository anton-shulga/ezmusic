package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class CreateAuthorService {
    public static boolean create(Author instance) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            return dao.create(instance);
        } catch (DAOException e) {
            throw new ServiceException("Creating author error", e);
        }
    }
}
