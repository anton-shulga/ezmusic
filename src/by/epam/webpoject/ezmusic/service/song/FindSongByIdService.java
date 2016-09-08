package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;
import by.epam.webpoject.ezmusic.service.comment.FindCommentBySongIdService;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByIdService  {
    public static Song find(Long songId) throws ServiceException {
        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();
        Song song = null;
        try {
            song = songDao.find(songId);
            song.setAlbumList(FindAlbumsBySongIdService.find(songId));
            song.setAuthorList(FindAuthorsBySongIdService.find(songId));
            song.setCommentList(FindCommentBySongIdService.find(songId));
        } catch (DAOException e) {
            throw new ServiceException("Find song service exception", e);
        }
        return song;
    }

}
