package by.epam.webpoject.ezmusic.service.comment;

import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindCommentBySongIdService {
    public static ArrayList<Comment> find(Long songId) throws ServiceException {
        CommentDAO dao = (CommentDAO) DAOFactory.createCommentDAO();
        UserDAO userDAO = (UserDAO) DAOFactory.createUserDAO();
        ArrayList<Comment> commentList = null;
        try {
            commentList =  dao.findBySongId(songId);
            for (Comment comment : commentList){
                User user = userDAO.find(comment.getUser().getUserId());
                comment.setUser(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("Finding comment error", e);
        }
        return commentList;
    }
}
