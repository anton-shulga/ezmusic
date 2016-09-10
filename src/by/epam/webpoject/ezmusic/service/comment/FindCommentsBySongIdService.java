package by.epam.webpoject.ezmusic.service.comment;

import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindCommentsBySongIdService {
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
            throw new ServiceException("Find comments by song id service exception", e);
        }
        return commentList;
    }
}
