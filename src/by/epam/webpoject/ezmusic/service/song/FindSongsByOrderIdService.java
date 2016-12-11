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
 * Created by Антон on 08.09.2016.
 */
public class FindSongsByOrderIdService {
    public static ArrayList<Song> find(Long orderId) throws ServiceException {

        ArrayList<Song> songList = null;

        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();

        try {
            songList = songDAO.findByOrderId(orderId);
            for (Song song : songList) {
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setCommentList(FindCommentsBySongIdService.find(song.getSongId()));
                song.setTagList(songDAO.findTagsBySongId(song.getSongId()));
                song.setRewardList(songDAO.findRewardsBySongId(song.getSongId()));
            }

            return songList;
        } catch (DAOException e) {
            throw new ServiceException("Find songs by order id service exception", e);
        }
    }
}
