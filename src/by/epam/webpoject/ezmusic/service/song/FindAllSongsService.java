package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;
import by.epam.webpoject.ezmusic.service.comment.FindCommentsBySongIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 12.08.2016.
 */
public class FindAllSongsService {
    public static ArrayList<Song> find() throws ServiceException {

        ArrayList<Song> allSongs = null;

        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();

        try {
            allSongs = dao.findAll();

            for(Song song : allSongs){
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
                song.setCommentList(FindCommentsBySongIdService.find(song.getSongId()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Find all songs service exception", e);
        }
        return allSongs;
    }
}
