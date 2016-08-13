package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */
public abstract class SongDAO implements AbstractDAO<Song, Long> {
    private static final Logger LOGGER = LogManager.getLogger(SongDAO.class);

    public abstract ArrayList<Song> findByUserId(Long userId) throws DAOException;
    public abstract ArrayList<Song> findByAlbumId(Long albumId) throws DAOException;
    public abstract ArrayList<Song> findAll() throws DAOException;

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
