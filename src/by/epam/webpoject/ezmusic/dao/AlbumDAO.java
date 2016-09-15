package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public abstract class AlbumDAO implements AbstractDAO<Album, Long> {
    private final static Logger LOGGER = LogManager.getLogger(AlbumDAO.class);

    public abstract ArrayList<Album> findByAuthorId(Long authorId) throws DAOException;
    public abstract ArrayList<Album> findAll() throws DAOException;
    public abstract ArrayList<Album> findBySongId(Long songId) throws DAOException;
    public abstract boolean createAlbumSong(Long albumId, Long songId) throws DAOException;
    public abstract boolean createAlbumAuthor(Long albumId, Long authorId) throws DAOException;
    public abstract void deleteAlbumSong(Long albumId) throws DAOException;
    public abstract void deleteAlbumAuthor(Long albumId) throws DAOException;
    public abstract ArrayList<Album> findBySearchRequest(String searchRequest) throws DAOException;

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
