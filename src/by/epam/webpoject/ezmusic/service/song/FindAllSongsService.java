package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 12.08.2016.
 */
public class FindAllSongsService {
    public static ArrayList<Song> find() throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Song> allSongs = new ArrayList<>();
        try {
            allSongs = dao.findAll();
            ArrayList<Author> songAuthors = null;
            ArrayList<Album> songAlbums = null;
            for(Song song : allSongs){
                songAuthors  = FindAuthorsBySongIdService.find(song.getSongId());
                songAlbums = FindAlbumsBySongIdService.find(song.getSongId());
                song.setAuthorList(songAuthors);
                song.setAlbumList(songAlbums);
            }
        } catch (DAOException e) {
            throw new ServiceException("Find songs service exception", e);
        }
        return allSongs;
    }
}
