package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 18.08.2016.
 */
public class FindAuthorByAlbumIdService {
    public static ArrayList<Author> find(Long albumId) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            return dao.findByAlbumId(albumId);
        } catch (CommandException e) {
            throw new ServiceException("Find author service exception", e);
        }
    }
}
