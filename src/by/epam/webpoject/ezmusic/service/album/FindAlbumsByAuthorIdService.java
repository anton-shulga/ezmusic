package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindAlbumsByAuthorIdService {
    public static ArrayList<Album> find(Long authorId) throws ServiceException {

        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        ArrayList<Album> albumList = null;
        try {
            albumList = albumDAO.findByAuthorId(authorId);
            for(Album album : albumList){
                album.setRewardList(albumDAO.findRewardsByAlbumId(album.getAlbumId()));
            }
            return albumList;
        } catch (DAOException e) {
            throw new ServiceException("Find albums by author id service exception", e);
        }
    }
}
