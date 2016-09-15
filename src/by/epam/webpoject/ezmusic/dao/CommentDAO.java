package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 08.08.2016.
 */
public interface CommentDAO extends AbstractDAO<Comment, Long> {

    ArrayList<Comment> findBySongId(Long songId) throws DAOException;

}
