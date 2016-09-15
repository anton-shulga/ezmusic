package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public interface AlbumDAO extends AbstractDAO<Album, Long> {

    ArrayList<Album> findByAuthorId(Long authorId) throws DAOException;

    ArrayList<Album> findAll() throws DAOException;

    ArrayList<Album> findBySongId(Long songId) throws DAOException;

    boolean createAlbumSong(Long albumId, Long songId) throws DAOException;

    boolean createAlbumAuthor(Long albumId, Long authorId) throws DAOException;

    void deleteAlbumSong(Long albumId) throws DAOException;

    void deleteAlbumAuthor(Long albumId) throws DAOException;

    ArrayList<Album> findBySearchRequest(String searchRequest) throws DAOException;

}
