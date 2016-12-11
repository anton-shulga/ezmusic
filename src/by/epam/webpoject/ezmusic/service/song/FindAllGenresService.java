package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Genre;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class FindAllGenresService {
    public static ArrayList<Genre> findAll() throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Genre> genreList = null;
        try {
            return songDAO.findAllGenres();
        } catch (DAOException e) {
           throw new ServiceException("Find all genres exception", e);
        }
    }
}
