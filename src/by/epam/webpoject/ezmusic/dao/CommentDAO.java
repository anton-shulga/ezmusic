package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 08.08.2016.
 */
public abstract class CommentDAO implements AbstractDAO<Comment, Long> {
    private static final Logger LOGGER = LogManager.getLogger(CommentDAO.class);

    public abstract ArrayList<Comment> findBySongId(Long songId) throws DAOException;

    @Override
    public void closeStatement(Statement statement) {
        try {
            if(statement != null)
                statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
