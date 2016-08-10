package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindAlbumByAuthorIdService {
    public static ArrayList<Album> find(Long authorId) throws ServiceException {
        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            return dao.findByAuthorId(authorId);
        } catch (DAOException e) {
            throw new ServiceException("Finding album error", e);
        }
    }
}
