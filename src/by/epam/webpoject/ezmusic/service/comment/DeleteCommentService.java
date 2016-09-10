package by.epam.webpoject.ezmusic.service.comment;

import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;


/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteCommentService {
    public static void delete(Long commentId) throws ServiceException {
        CommentDAO dao = (CommentDAO) DAOFactory.createCommentDAO();
        try {
            dao.delete(commentId);
        } catch (DAOException e) {
            throw new ServiceException("Delete comment service exception", e);
        }
    }
}
