package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.exception.DAOException;

import java.sql.Statement;


/**
 * Created by Антон on 16.07.2016.
 */
public interface AbstractDAO<T, K> {
    K create(T instance) throws DAOException;
    T find(K id) throws DAOException;
    void delete(K id) throws DAOException;
    void update(T instance) throws DAOException;
    void closeStatement(Statement statement);
}
