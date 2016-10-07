package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 08.08.2016.
 */
/**
 * Interface for for a Data Access Object that uses for Comment entity {@link Comment}
 */
public interface CommentDAO extends AbstractDAO<Comment, Long> {

    /**
     * Find comment by specified song id
     *
     * @param songId specified song id
     * @return list of comments, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Comment> findBySongId(Long songId) throws DAOException;

}
