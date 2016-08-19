package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateSongService {
    public static Long create(Song instance, Long[] albumIds, Long[] authorIds) throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        Long generatedId = null;
        try {
            generatedId = dao.create(instance);
            if(albumIds != null) {
                for (Long albumId : albumIds) {
                    dao.createSongAlbum(generatedId, albumId);
                }
            }
            if(authorIds != null) {
                for (Long authorId : authorIds) {
                    dao.createSongAuthor(generatedId, authorId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Creating song error", e);
        }
        return generatedId;
    }
}
