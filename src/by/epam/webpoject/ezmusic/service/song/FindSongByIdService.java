package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;
import by.epam.webpoject.ezmusic.service.comment.FindCommentsBySongIdService;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByIdService  {
    public static Song find(Long songId) throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        Song song = null;
        try {
            song = songDAO.find(songId);
            song.setAlbumList(FindAlbumsBySongIdService.find(songId));
            song.setAuthorList(FindAuthorsBySongIdService.find(songId));
            song.setCommentList(FindCommentsBySongIdService.find(songId));
        } catch (DAOException e) {
            throw new ServiceException("Find song service exception", e);
        }
        return song;
    }

}
