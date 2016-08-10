package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumService {
    public static boolean create(Album instance) throws ServiceException {
        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            return dao.create(instance);
        } catch (DAOException e) {
            throw new ServiceException("Creating album error", e);
        }
    }
}
