package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByIdService  {
    public static Song find(Long songId) throws ServiceException {
        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        Song song = null;
        try {
            song = songDao.find(songId);
            song.setAlbumList(albumDAO.findBySongId(songId));
            song.setAuthorList(authorDAO.findBySongId(songId));
        } catch (DAOException e) {
            throw new ServiceException("Finding song error", e);
        }
        return song;
    }

}
