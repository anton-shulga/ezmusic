package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByAlbumIdService {
    public static ArrayList<Song> find(Long albumId) throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        try {
            return dao.findByAlbumId(albumId);
        } catch (DAOException e) {
            throw new ServiceException("Finding song error", e);
        }
    }
}
