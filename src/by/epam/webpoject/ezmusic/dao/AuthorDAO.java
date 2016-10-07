package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 15.08.2016.
 */
/**
 * Interface for for a Data Access Object that uses for Author entity {@link Author}
 */
public interface AuthorDAO extends AbstractDAO<Author, Long> {

    /**
     * Find all authors
     *
     * @return list of authors, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Author> findAll() throws DAOException;

    /**
     * Find author by specified song id
     *
     * @param songId specified song id
     * @return list of authors, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Author> findBySongId(Long songId) throws DAOException;

    /**
     * Find author by specified albums id
     *
     * @param albumId specified album id
     * @return list of albums, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Author> findByAlbumId(Long albumId) throws CommandException;

    /**
     * Associate author and album entities by specified ids
     *
     * @param authorId specified author id
     * @param albumId  specified album id
     * @throws DAOException if database error was detected
     */
    Long createAuthorAlbum(Long authorId, Long albumId) throws DAOException;

    /**
     * Associate author and album entities by specified ids
     *
     * @param authorId specified author id
     * @param songId   specified song id
     * @throws DAOException if database error was detected
     */
    Long createAuthorSong(Long authorId, Long songId) throws DAOException;


    /**
     * Remove the association author album by specified author id
     *
     * @param authorId specified album id
     * @throws DAOException if database error was detected
     */
    void deleteAuthorAlbum(Long authorId) throws DAOException;


    /**
     * Remove the association author song by specified author id
     *
     * @param authorId specified album id
     * @throws DAOException if database error was detected
     */
    void deleteAuthorSong(Long authorId) throws DAOException;

    /**
     * Find authors entities by specified expression
     * @param searchRequest specified expression
     * @return list of founded authors, may return empty list
     * @throws DAOException if database error was detected
     */
    ArrayList<Author> findBySearchRequest(String searchRequest) throws DAOException;

}
