package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.AlbumType;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */

public class FindAllAlbumTypesService {

    public static ArrayList<AlbumType> find() throws ServiceException {
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            return albumDAO.findAllAlbumTypes();
        } catch (DAOException e) {
            throw new ServiceException("Find ex", e);
        }
    }
}
