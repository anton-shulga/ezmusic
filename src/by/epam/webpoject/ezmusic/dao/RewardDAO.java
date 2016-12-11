package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public interface RewardDAO extends AbstractDAO<Reward, Long>{
    ArrayList<Reward> findAllAlbumReward() throws DAOException;
    ArrayList<Reward> findAllSongRewards() throws DAOException;
    void createRewardSong(Long rewardId, Long songId) throws DAOException;
    void createRewardAlbum(Long rewardId, Long albumId) throws DAOException;
    void deleteRewardSong(Long rewardId, Long songId) throws DAOException;
    void deleteRewardAlbum(Long rewardId, Long albumId) throws DAOException;
}
