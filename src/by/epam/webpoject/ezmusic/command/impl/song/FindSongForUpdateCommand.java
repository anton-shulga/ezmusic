package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.song.FindSongByIdService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongForUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Song song = null;
        ArrayList<Album> albumList = null;
        ArrayList<Author> authorList = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);

        boolean isValidRequest = SongParametersValidator.validateFindParameters(songId);
        if (isValidRequest) {
            try {
                song = FindSongByIdService.find(Long.parseLong(songId));

                albumList = FindAllAlbumsService.find();
                authorList = FindAllAuthorsService.find();

                if (song != null && authorList != null && albumList != null) {
                    request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                    request.setAttribute(RequestParameter.SONG_AUTHORS, song.getAuthorList());
                    request.setAttribute(RequestParameter.SONG_ALBUMS, song.getAlbumList());
                    request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                    request.setAttribute(RequestParameter.SONG, song);
                    page = JspPageName.ADMIN_EDIT_SONG;
                } else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                    page = JspPageName.ADMIN_HOME;
                }
            } catch (ServiceException e) {
                throw new CommandException("Find song command exception", e);
            }

        } else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.ADMIN_HOME;
        }

        return page;
    }
}
