package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 15.09.2016.
 */
public class FIndAuthorsBySearchRequestService {
    public static ArrayList<Author> find(String searchRequest) throws ServiceException {
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            return authorDAO.findBySearchRequest(searchRequest);
        } catch (DAOException e) {
            throw new ServiceException("Find authors by search request service exception", e);
        }
    }
}
