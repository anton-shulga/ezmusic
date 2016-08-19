package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateAlbumService {
    public static void update(Album instance, Long[] songIds, Long[] authorIds) throws ServiceException {
        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            dao.update(instance);
            dao.deleteAlbumSong(instance.getAlbumId());
            if(songIds != null) {
                for (Long songId : songIds) {
                    dao.createAlbumSong(instance.getAlbumId(), songId);
                }
            }
            dao.deleteAlbumAuthor(instance.getAlbumId());
            if(authorIds != null) {
                for (Long authorId : authorIds) {
                    dao.createAlbumAuthor(instance.getAlbumId(), authorId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Updating album error", e);
        }
    }
}
