package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class DeleteAuthorService {
    public static void delete(Long authorId) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            dao.delete(authorId);
        } catch (DAOException e) {
            throw new ServiceException("Delete author service exception", e);
        }
    }
}
