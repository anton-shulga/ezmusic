package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.Tag;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public interface TagDAO extends AbstractDAO<Tag, Long> {
    ArrayList<Tag> findAllTags() throws DAOException;
    void createTagSong(Long tagId, Long songId) throws DAOException;
    void deleteTagSong(Long tagId, Long songId) throws DAOException;
}
