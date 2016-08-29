package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 17.08.2016.
 */
public class FindAuthorsBySongIdService {
    public static ArrayList<Author> find(Long songId) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            return dao.findBySongId(songId);
        } catch (DAOException e) {
            throw new ServiceException("Finding author error", e);
        }
    }
}
