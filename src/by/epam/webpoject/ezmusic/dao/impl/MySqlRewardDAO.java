package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.dao.RewardDAO;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class MySqlRewardDAO implements RewardDAO {



    @Override
    public Long create(Reward instance) throws DAOException {
        return null;
    }

    @Override
    public Reward find(Long id) throws DAOException {
        return null;
    }

    @Override
    public void update(Reward instance) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }

    @Override
    public ArrayList<Reward> findAllAlbumReward() throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Reward> findAllSongRewards() throws DAOException {
        return null;
    }

    @Override
    public void createRewardSong(Long rewardId, Long songId) throws DAOException {

    }

    @Override
    public void createRewardAlbum(Long rewardId, Long albumId) throws DAOException {

    }

    @Override
    public void deleteRewardSong(Long rewardId, Long songId) throws DAOException {

    }

    @Override
    public void deleteRewardAlbum(Long rewardId, Long albumId) throws DAOException {

    }
}
