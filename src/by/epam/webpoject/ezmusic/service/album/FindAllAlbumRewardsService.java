package by.epam.webpoject.ezmusic.service.album;

import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 12.12.2016.
 */
public class FindAllAlbumRewardsService {
    public static ArrayList<Reward> find() throws ServiceException {
        AlbumDAO albumDAO = (AlbumDAO) DAOFactory.createAlbumDAO();
        try {
            return albumDAO.findAllAlbumReward();
        } catch (DAOException e) {
            throw new ServiceException("Find err", e);

        }
    }
}
