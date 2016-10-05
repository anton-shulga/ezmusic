package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 30.08.2016.
 */
public class AddSongToOrderService {
    public static void add(Long songId, Order cart) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();

        Song song = null;

        try {
            song = FindSongByIdService.find(songId);

            if (!cart.getSongList().contains(song)) {
                songDAO.createSongOrder(songId, cart.getOrderId());
            }
        } catch (DAOException e) {
            throw new ServiceException("Add song to order service exception");
        }
    }
}
