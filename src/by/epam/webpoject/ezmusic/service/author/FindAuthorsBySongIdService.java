package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 17.08.2016.
 */
public class FindAuthorsBySongIdService {
    public static ArrayList<Author> find(Long songId) throws ServiceException {

        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();

        try {
            return authorDAO.findBySongId(songId);
        } catch (DAOException e) {
            throw new ServiceException("Find authors by song id service exception", e);
        }
    }
}
