package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.dao.TagDAO;
import by.epam.webpoject.ezmusic.entity.Tag;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class MySqlTagDAO implements TagDAO {


    @Override
    public Long create(Tag instance) throws DAOException {
        return null;
    }

    @Override
    public Tag find(Long id) throws DAOException {
        return null;
    }

    @Override
    public void update(Tag instance) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }

    @Override
    public ArrayList<Tag> findAllTags() throws DAOException {
        return null;
    }

    @Override
    public void createTagSong(Long tagId, Long songId) throws DAOException {

    }

    @Override
    public void deleteTagSong(Long tagId, Long songId) throws DAOException {

    }
}
