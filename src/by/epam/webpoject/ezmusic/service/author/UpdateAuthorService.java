package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class UpdateAuthorService {
    public static void update(Author instance, Long[] albumIds, Long[] songIds) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            dao.update(instance);
            dao.deleteAuthorAlbum(instance.getAuthorId());
            if(albumIds != null){
                for(Long albumId : albumIds){
                    dao.createAuthorAlbum(instance.getAuthorId(), albumId);
                }
            }
            dao.deleteAuthorSong(instance.getAuthorId());
            if(songIds != null){
                for(Long songId : songIds){
                    dao.createAuthorSong(instance.getAuthorId(), songId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Updating author error", e);
        }
    }
}
