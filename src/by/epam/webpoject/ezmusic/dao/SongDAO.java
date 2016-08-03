package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

/**
 * Created by Антон on 04.08.2016.
 */
public abstract class SongDAO implements AbstractDAO<Song, Long> {
    public abstract Song findByUserId(Long userId) throws DAOException;
}
