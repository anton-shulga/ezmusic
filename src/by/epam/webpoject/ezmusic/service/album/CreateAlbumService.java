package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumService {
    public static Long create(Album instance, Long[] songIds, Long[] authorIds) throws ServiceException {

        Long generatedId = null;

        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();

        try {
            generatedId = albumDAO.create(instance);
            if (songIds != null) {
                for (Long songId : songIds) {
                    albumDAO.createAlbumSong(generatedId, songId);
                }
            }
            if (authorIds != null) {
                for (Long authorId : authorIds) {
                    albumDAO.createAlbumAuthor(generatedId, authorId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Create album service exception", e);
        }

        return generatedId;
    }
}
