package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 15.08.2016.
 */
public abstract class AuthorDAO implements AbstractDAO<Author, Long> {
    private static final Logger LOGGER = LogManager.getLogger(AuthorDAO.class);

    public abstract ArrayList<Author> findAll() throws DAOException;
    public abstract ArrayList<Author> findBySongId(Long songId) throws DAOException;
    public abstract ArrayList<Author> findByAlbumId(Long albumId) throws CommandException;
    public abstract Long createAuthorAlbum(Long authorId, Long albumId) throws DAOException;
    public abstract Long createAuthorSong(Long authorId, Long songId) throws DAOException;
    public abstract void deleteAuthorAlbum(Long authorId) throws DAOException;
    public abstract void deleteAuthorSong(Long authorId) throws DAOException;

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
