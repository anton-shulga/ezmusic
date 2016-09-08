package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 02.09.2016.
 */
public class DeleteSongFromCartService {
    public static void delete(Long songId, Order cart) throws ServiceException {
        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();
        try {
            songDao.deleteSongOrder(songId, cart.getOrderId());
        } catch (DAOException e) {
            throw new ServiceException("Delete song from order service exception", e);
        }
    }
}
