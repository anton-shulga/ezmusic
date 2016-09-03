package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.CommentDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.comment.FindCommentBySongIdService;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongByIdService  {
    public static Song find(Long songId) throws ServiceException {
        SongDAO songDao = (SongDAO) DAOFactory.createSongDAO();
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createCommentDAO();
        Song song = null;
        try {
            song = songDao.find(songId);
            song.setAlbumList(albumDAO.findBySongId(songId));
            song.setAuthorList(authorDAO.findBySongId(songId));
            song.setCommentList(commentDAO.findBySongId(songId));
            song.setCommentList(FindCommentBySongIdService.find(songId));
        } catch (DAOException e) {
            throw new ServiceException("Finding song error", e);
        }
        return song;
    }

}
