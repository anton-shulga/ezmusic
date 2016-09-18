package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */
public interface SongDAO extends AbstractDAO<Song, Long> {

    ArrayList<Song> findByAlbumId(Long albumId) throws DAOException;

    ArrayList<Song> findByAuthorId(Long authorId) throws DAOException;

    ArrayList<Song> findByOrderId(Long orderId) throws DAOException;

    ArrayList<Song> findAll() throws DAOException;

    boolean createSongAlbum(Long songId, Long albumId) throws DAOException;

    boolean createSongAuthor(Long songId, Long authorId) throws DAOException;

    boolean createSongOrder(Long songId, Long orderId) throws DAOException;

    void deleteSongAlbum(Long songId) throws DAOException;

    void deleteSongAuthor(Long songId) throws DAOException;

    void deleteSongOrder(Long songId, Long albumId) throws DAOException;

    ArrayList<Song> findBySearchRequest(String searchRequest) throws DAOException;

    boolean isOrderedSong(Long songId) throws DAOException;


}
