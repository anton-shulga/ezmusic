package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.exception.dao.DAOException;

/**
 * Created by Антон on 16.07.2016.
 */
public interface AbstractDAO<T, K> {
    boolean create(T instance) throws DAOException;
    T find(K id) throws DAOException;
    void delete(K id) throws DAOException;
    void update(T instance) throws DAOException;
}
