package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 18.09.2016.
 */
public class IsOrderedSongService {
    public static boolean isOrdered(Long songId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        try {
            return songDAO.isOrderedSong(songId);
        } catch (DAOException e) {
            throw new ServiceException("Is ordered song service", e);
        }
    }
}
