package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;
import by.epam.webpoject.ezmusic.service.comment.FindCommentBySongIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 08.09.2016.
 */
public class FindSongsByOrderIdService {
    public static ArrayList<Song> find(Long orderId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Song> songList = null;
        try {
            songList = songDAO.findByOrderId(orderId);
            for(Song song : songList){
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setCommentList(FindCommentBySongIdService.find(song.getSongId()));
            }
            return songList;

        } catch (DAOException e) {
            throw new ServiceException("Find songs by order id service exception", e);
        }
    }
}
