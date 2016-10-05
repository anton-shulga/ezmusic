package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.Date;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateSongService {
    public static void update(Song instance) throws ServiceException {

        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();

        try {
            Date javaDate = new Date();
            instance.setPublicationDate(new java.sql.Date(javaDate.getTime()));

            songDAO.update(instance);

            songDAO.deleteSongAlbum(instance.getSongId());

            if(instance.getAlbumList() != null) {
                for (Album album : instance.getAlbumList()) {
                    songDAO.createSongAlbum(instance.getSongId(), album.getAlbumId());
                }
            }

            songDAO.deleteSongAuthor(instance.getSongId());

            if(instance.getAuthorList() != null) {
                for (Author author : instance.getAuthorList()) {
                    songDAO.createSongAuthor(instance.getSongId(), author.getAuthorId());
                }
            }

        } catch (DAOException e) {
            throw new ServiceException("Update song service exeption", e);
        }
    }
}
