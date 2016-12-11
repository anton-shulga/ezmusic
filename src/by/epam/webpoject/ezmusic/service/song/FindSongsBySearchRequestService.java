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
 * Created by Антон on 15.09.2016.
 */
public class FindSongsBySearchRequestService {
    public static ArrayList<Song> find(String searchRequest) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Song> songList = null;
        try {
            songList = songDAO.findBySearchRequest(searchRequest);
            for (Song song : songList) {
                song.setAuthorList(FindAuthorsBySongIdService.find(song.getSongId()));
                song.setAlbumList(FindAlbumsBySongIdService.find(song.getSongId()));
                song.setCommentList(FindCommentsBySongIdService.find(song.getSongId()));
                song.setTagList(songDAO.findTagsBySongId(song.getSongId()));
                song.setRewardList(songDAO.findRewardsBySongId(song.getSongId()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Find songs by search request service exception");
        }
        return songList;
    }
}
