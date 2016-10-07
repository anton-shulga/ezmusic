package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 15.08.2016.
 */
public class UpdateAuthorService {
    public static void update(Author instance, Long[] albumIds, Long[] songIds) throws ServiceException {

        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();

        try {
            authorDAO.update(instance);

            authorDAO.deleteAuthorAlbum(instance.getAuthorId());

            if (albumIds != null) {
                for (Long albumId : albumIds) {
                    authorDAO.createAuthorAlbum(instance.getAuthorId(), albumId);
                }
            }

            authorDAO.deleteAuthorSong(instance.getAuthorId());

            if (songIds != null) {
                for (Long songId : songIds) {
                    authorDAO.createAuthorSong(instance.getAuthorId(), songId);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Update author service exception", e);
        }
    }
}
