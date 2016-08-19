package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumService {
    public static Long create(Album instance, Long[] songIds, Long[] authorIds) throws ServiceException {
        Long generatedId = null;
        AlbumDAO dao = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            generatedId =  dao.create(instance);
            for(Long songId : songIds){
                dao.createAlbumSong(generatedId, songId);
            }
            for (Long authorId : authorIds){
                dao.createAlbumAuthor(generatedId, authorId);
            }
        } catch (DAOException e) {
            throw new ServiceException("Creating album error", e);
        }
        return generatedId;
    }
}
