package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class CreateAuthorService {
    public static Long create(Author instance, Long[] albumIds, Long[] songIds) throws ServiceException {
        AuthorDAO dao = (AuthorDAO) DAOFactory.createAuthorDAO();
        Long generatedId = null;
        try {
            generatedId = dao.create(instance);
            if(albumIds != null){
                for(Long albumId:albumIds){
                    dao.createAuthorAlbum(generatedId, albumId);
                }
            }
            if(songIds != null){
                for(Long songId:songIds){
                    dao.createAuthorAlbum(generatedId, songId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Create album service exception", e);
        }
        return generatedId;
    }
}
