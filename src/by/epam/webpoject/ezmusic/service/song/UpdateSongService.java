package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateSongService {
    public static void update(Song instance, Long[] albumIds) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            songDAO.update(instance);
            songDAO.deleteSongAlbum(instance.getSongId());
            for (Long albumId : albumIds) {
                songDAO.createSongAlbum(instance.getSongId(), albumId);
            }
        } catch (DAOException e) {
            throw new ServiceException("Updating song error", e);
        }
    }
}
