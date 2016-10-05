package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class CreateAuthorService {
    public static Long create(Author instance, Long[] albumIds, Long[] songIds) throws ServiceException {

        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();

        Long generatedId = null;

        try {
            generatedId = authorDAO.create(instance);

            if(albumIds != null){
                for(Long albumId:albumIds){
                    authorDAO.createAuthorAlbum(generatedId, albumId);
                }
            }

            if(songIds != null){
                for(Long songId:songIds){
                    authorDAO.createAuthorSong(generatedId, songId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Create author service exception", e);
        }
        return generatedId;
    }
}
