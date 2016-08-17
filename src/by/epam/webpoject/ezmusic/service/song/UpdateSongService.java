package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateSongService {
    public static void update(Song instance, Long[] albumIds, Long[] authorIds) throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        try {
            dao.update(instance);
            dao.deleteSongAlbum(instance.getSongId());
            for (Long albumId : albumIds) {
                dao.createSongAlbum(instance.getSongId(), albumId);
            }
            dao.deleteSongAuthor(instance.getSongId());
            for (Long authorId : authorIds) {
                dao.createSongAuthor(instance.getSongId(), authorId);
            }
        } catch (DAOException e) {
            throw new ServiceException("Updating song error", e);
        }
    }
}
