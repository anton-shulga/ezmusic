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
 * Created by Антон on 10.08.2016.
 */
public class FindSongsByAlbumIdService {
    public static ArrayList<Song> find(Long albumId) throws ServiceException {

        ArrayList<Song> songList = null;

        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();

        try {
            songList = songDao.findByAlbumId(albumId);
            for(Song song : songList){
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setCommentList(FindCommentsBySongIdService.find(song.getSongId()));
            }

        } catch (DAOException e) {
            throw new ServiceException("Find songs by album id service exception", e);
        }
        return songList;
    }
}
