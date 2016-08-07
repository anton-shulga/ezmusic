package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public abstract class AlbumDAO implements AbstractDAO<Album, Long> {
    public abstract ArrayList<Album> findByAuthorId(Long authorId) throws DAOException;
}
