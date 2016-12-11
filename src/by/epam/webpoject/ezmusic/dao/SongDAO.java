package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Genre;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.entity.Tag;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */

/**
 * Interface for for a Data Access Object that uses for Song entity {@link Song}
 */
public interface SongDAO extends AbstractDAO<Song, Long> {
    /**
     * Find song by specified album id
     *
     * @param albumId specified album id
     * @return list of songs, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Song> findByAlbumId(Long albumId) throws DAOException;

    /**
     * Find song by specified author id
     *
     * @param authorId specified author id
     * @return list of songs, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Song> findByAuthorId(Long authorId) throws DAOException;

    /**
     * Find song by specified order id
     *
     * @param orderId specified order id
     * @return list of songs, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Song> findByOrderId(Long orderId) throws DAOException;

    /**
     * Find all songs
     *
     * @return list of songs, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Song> findAll() throws DAOException;

    /**
     * Associate song and albums entities by specified ids
     *
     * @param songId  specified song id
     * @param albumId specified album id
     * @throws DAOException if database error was detected
     */
    boolean createSongAlbum(Long songId, Long albumId) throws DAOException;

    /**
     * Associate song and author entities by specified ids
     *
     * @param songId   specified song id
     * @param authorId specified author id
     * @throws DAOException if database error was detected
     */
    boolean createSongAuthor(Long songId, Long authorId) throws DAOException;

    /**
     * Associate song and albums entities by specified ids
     *
     * @param songId  specified song id
     * @param orderId specified order id
     * @throws DAOException if database error was detected
     */
    boolean createSongOrder(Long songId, Long orderId) throws DAOException;

    /**
     * Remove association song  albums by specified songId
     *
     * @param songId specified song id
     * @throws DAOException if database error was detected
     */
    void deleteSongAlbum(Long songId) throws DAOException;

    /**
     * Remove association songauthor by specified songId
     *
     * @param songId specified song id
     * @throws DAOException if database error was detected
     */
    void deleteSongAuthor(Long songId) throws DAOException;

    /**
     * Remove association song order by specified songId
     *
     * @param songId specified song id
     * @throws DAOException if database error was detected
     */
    void deleteSongOrder(Long songId, Long albumId) throws DAOException;

    /**
     * Find songs entities by specified expression
     *
     * @param searchRequest specified expression
     * @return list of founded songs, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Song> findBySearchRequest(String searchRequest) throws DAOException;

    /**
     * Check if song is already ordered by specified song id
     *
     * @param songId specified song id
     * @return true if song is ordered, otherwise return false
     * @throws DAOException if database error was detected
     */
    boolean isOrderedSong(Long songId) throws DAOException;

    ArrayList<Tag> findAllTags() throws DAOException;
    void createTagSong(Long tagId, Long songId) throws DAOException;
    void deleteTagSong(Long tagId) throws DAOException;

    ArrayList<Reward> findAllSongRewards() throws DAOException;
    void createRewardSong(Long rewardId, Long songId) throws DAOException;

    void deleteRewardSong(Long songId) throws DAOException;

    ArrayList<Genre> findAllGenres() throws DAOException;
    ArrayList<Tag> findTagsBySongId(Long songId) throws DAOException;

    ArrayList<Reward> findRewardsBySongId(Long songId) throws DAOException;
}
