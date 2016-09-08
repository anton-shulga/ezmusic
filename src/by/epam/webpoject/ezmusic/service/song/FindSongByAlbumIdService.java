package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByAlbumIdService {
    public static ArrayList<Song> find(Long albumId) throws ServiceException {
        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        ArrayList<Song> songList = null;
        try {
            songList = songDao.findByAlbumId(albumId);
            for(Song song : songList){
                song.setAlbumList(albumDAO.findBySongId(song.getSongId()));
                song.setAuthorList(authorDAO.findBySongId(song.getSongId()));
            }

        } catch (DAOException e) {
            throw new ServiceException("Find song service exception", e);
        }
        return songList;
    }
}
