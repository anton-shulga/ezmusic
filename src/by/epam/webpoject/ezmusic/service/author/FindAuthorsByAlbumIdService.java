package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 18.08.2016.
 */
public class FindAuthorsByAlbumIdService {
    public static ArrayList<Author> find(Long albumId) throws ServiceException {

        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();

        try {
            return authorDAO.findByAlbumId(albumId);
        } catch (CommandException e) {
            throw new ServiceException("Find authors by album id service exception", e);
        }
    }
}
