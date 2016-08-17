package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 16.08.2016.
 */
public class FindAllAlbumsService {
    public static ArrayList<Album> find() throws ServiceException {
        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            return dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Finding albums error", e);
        }
    }
}
