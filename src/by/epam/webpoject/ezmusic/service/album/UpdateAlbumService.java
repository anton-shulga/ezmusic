package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateAlbumService {
    public static void update(Album instance, Long[] songIds, Long[] authorIds) throws ServiceException {

        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();

        try {
            albumDAO.update(instance);

            albumDAO.deleteAlbumSong(instance.getAlbumId());

            if (songIds != null) {
                for (Long songId : songIds) {
                    albumDAO.createAlbumSong(instance.getAlbumId(), songId);
                }
            }

            albumDAO.deleteAlbumAuthor(instance.getAlbumId());

            if (authorIds != null) {
                for (Long authorId : authorIds) {
                    albumDAO.createAlbumAuthor(instance.getAlbumId(), authorId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Update album service exception", e);
        }
    }
}
