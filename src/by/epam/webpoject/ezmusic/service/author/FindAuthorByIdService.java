package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class FindAuthorByIdService {
    public static Author find(Long authorId) throws ServiceException {

        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();

        try {
            return authorDAO.find(authorId);
        } catch (DAOException e) {
            throw new ServiceException("Find author by id service exception", e);
        }
    }
}
