package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Антон on 15.09.2016.
 */
public class FindAlbumsBySearchRequestService {
    public static ArrayList<Album> find(String searchRequest) throws ServiceException {
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        ArrayList<Album> albumList = null;
        try {
            albumList = albumDAO.findBySearchRequest(searchRequest);
            for(Album album : albumList){
                album.setRewardList(albumDAO.findRewardsByAlbumId(album.getAlbumId()));
            }

            return albumList;
        } catch (DAOException e) {
            throw new ServiceException("Find albums by search request service exception", e);
        }
    }
}
