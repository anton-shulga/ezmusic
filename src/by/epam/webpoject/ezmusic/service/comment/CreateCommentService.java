package by.epam.webpoject.ezmusic.service.comment;

import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateCommentService {
    public static Long create(Comment instance) throws ServiceException {

        CommentDAO dao = (CommentDAO) DAOFactory.createCommentDAO();

        try {
            return dao.create(instance);
        } catch (DAOException e) {
            throw new ServiceException("Create comment service exception", e);
        }
    }
}
