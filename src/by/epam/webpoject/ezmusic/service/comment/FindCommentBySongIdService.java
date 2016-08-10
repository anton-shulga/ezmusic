package by.epam.webpoject.ezmusic.service.comment;

import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindCommentBySongIdService {
    public static ArrayList<Comment> find(Long songId) throws ServiceException {
        CommentDAO dao = (CommentDAO) DAOFactory.createCommentDAO();
        try {
            return dao.findBySongId(songId);
        } catch (DAOException e) {
            throw new ServiceException("Finding comment error", e);
        }
    }
}
