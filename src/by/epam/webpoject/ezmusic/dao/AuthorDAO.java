package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 15.08.2016.
 */
public interface AuthorDAO extends AbstractDAO<Author, Long> {


    ArrayList<Author> findAll() throws DAOException;

    ArrayList<Author> findBySongId(Long songId) throws DAOException;

    ArrayList<Author> findByAlbumId(Long albumId) throws CommandException;

    Long createAuthorAlbum(Long authorId, Long albumId) throws DAOException;

    Long createAuthorSong(Long authorId, Long songId) throws DAOException;

    void deleteAuthorAlbum(Long authorId) throws DAOException;

    void deleteAuthorSong(Long authorId) throws DAOException;

    ArrayList<Author> findBySearchRequest(String searchRequest) throws DAOException;

}
