package by.epam.webpoject.ezmusic.service.song;

import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateSongService {
    public static void update(Song instance) throws ServiceException {
        SongDAO dao = (SongDAO) DAOFactory.createSongDAO();
        try {
            dao.update(instance);
            dao.deleteSongAlbum(instance.getSongId());
            if(instance.getAlbumList() != null) {
                for (Album album : instance.getAlbumList()) {
                    dao.createSongAlbum(instance.getSongId(), album.getAlbumId());
                }
            }
            dao.deleteSongAuthor(instance.getSongId());
            if(instance.getAuthorList() != null) {
                for (Author author : instance.getAuthorList()) {
                    dao.createSongAuthor(instance.getSongId(), author.getAuthorId());
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Update song service exeption", e);
        }
    }
}
