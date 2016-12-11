package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.AlbumType;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */

/**
 * Interface for for a Data Access Object that uses for Album entity {@link Album}
 */
public interface AlbumDAO extends AbstractDAO<Album, Long> {

    /**
     * Find album by specified author id
     *
     * @param authorId specified author id
     * @return list of albums, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Album> findByAuthorId(Long authorId) throws DAOException;

    /**
     * Find all albums
     *
     * @return list of albums, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Album> findAll() throws DAOException;

    /**
     * Find album by specified song id
     *
     * @param songId specified song id
     * @return list of albums, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Album> findBySongId(Long songId) throws DAOException;

    /**
     * Associate album and song entities by specified ids
     *
     * @param songId  specified song id
     * @param albumId specified album id
     * @throws DAOException if database error was detected
     */
    boolean createAlbumSong(Long albumId, Long songId) throws DAOException;

    /**
     * Associate album and author entities by specified ids
     *
     * @param authorId specified author id
     * @param albumId  specified album id
     * @throws DAOException if database error was detected
     */
    boolean createAlbumAuthor(Long albumId, Long authorId) throws DAOException;

    /**
     * Remove the association album song by specified album id
     *
     * @param albumId specified album id
     * @throws DAOException if database error was detected
     */
    void deleteAlbumSong(Long albumId) throws DAOException;

    /**
     * Remove the association album author by specified album id
     *
     * @param albumId specified album id
     * @throws DAOException if database error was detected
     */
    void deleteAlbumAuthor(Long albumId) throws DAOException;

    /**
     * Find albums entities by specified expression
     * @param searchRequest specified expression
     * @return list of founded albums, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Album> findBySearchRequest(String searchRequest) throws DAOException;

    ArrayList<AlbumType> findAllAlbumTypes() throws DAOException;
    ArrayList<Reward> findAllAlbumReward() throws DAOException;
    void deleteRewardAlbum(Long albumId) throws DAOException;
    void createRewardAlbum(Long rewardId, Long albumId) throws DAOException;
    ArrayList<Reward> findRewardsByAlbumId(Long albumId) throws DAOException;

}
