package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteSongService {
    public static void delete(Long id) throws ServiceException {

        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();

        try {
            songDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Delete song exception", e);
        }
    }
}
