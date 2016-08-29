package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindSongsByAuthorIdService {
    public static ArrayList<Song> find(Long authorId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        ArrayList<Song> songList = null;
        try {
            songList = songDAO.findByAuthorId(authorId);
            for (Song song : songList){
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Finding song error", e);
        }
        return songList;
    }
}
