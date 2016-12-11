package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.*;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.Date;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateSongService {
    public static Long create(Song instance) throws ServiceException {

        Long generatedId = null;

        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();

        try {
            Date javaDate = new Date();
            instance.setPublicationDate(new java.sql.Date(javaDate.getTime()));

            generatedId = songDAO.create(instance);

            if (instance.getAlbumList() != null) {
                for (Album album : instance.getAlbumList()) {
                    songDAO.createSongAlbum(generatedId, album.getAlbumId());
                }
            }
            if (instance.getAuthorList() != null) {
                for (Author author : instance.getAuthorList()) {
                    songDAO.createSongAuthor(generatedId, author.getAuthorId());
                }
            }

            if(instance.getTagList() != null){
                for(Tag tag : instance.getTagList()){
                    songDAO.createTagSong(tag.getTagId(), generatedId);
                }
            }

            if(instance.getRewardList() != null){
                for(Reward reward : instance.getRewardList()) {
                    songDAO.createRewardSong(reward.getRewardId(), generatedId);
                }
            }

        } catch (DAOException e) {
            throw new ServiceException("Create song service exception", e);
        }
        return generatedId;
    }
}
