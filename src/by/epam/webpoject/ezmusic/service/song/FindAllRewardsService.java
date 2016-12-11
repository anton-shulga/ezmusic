package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class FindAllRewardsService {
    public static ArrayList<Reward> find() throws ServiceException {
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        ArrayList<Reward> rewardList = null;

        try {
            return songDAO.findAllSongRewards();
        } catch (DAOException e) {
            throw new ServiceException("Find song service exception", e);
        }
    }
}
