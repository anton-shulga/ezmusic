package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 28.08.2016.
 */
public class FindAlbumsBySongIdService {
    public static ArrayList<Album> find(Long songId) throws ServiceException {

        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();

        try {
            return dao.findBySongId(songId);
        } catch (DAOException e) {
            throw new ServiceException("Find albums by song id service exception");
        }
    }
}
