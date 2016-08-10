package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteSongService {
    public static void delete(Long id) throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        try {
            dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Deleting song error", e);
        }
    }
}
