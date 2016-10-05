package test;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Антон on 26.09.2016.
 */
public class DAOTest {

    @Test
    public void findAllTest() throws DAOException, SQLException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Song> songList = songDAO.findAll();
        assertNotNull(songList);
    }
}
