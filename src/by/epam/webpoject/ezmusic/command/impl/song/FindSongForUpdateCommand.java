package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
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
        ArrayList<Album> allAlbums = null;
        ArrayList<Author> allAuthors = null;
        String songId = request.getParameter(RequestParameter.SONG_ID);
        boolean isValidRequest = SongParametersValidator.validateFindParameters(songId);
        if(isValidRequest){
            try {
                song = FindSongByIdService.find(Long.parseLong(songId));
                allAlbums = FindAllAlbumsService.find();
                allAuthors = FindAllAuthorsService.find();
            } catch (ServiceException e) {
                throw new CommandException("Find song command exception", e);
            }
            if(song != null  && allAuthors != null && allAlbums != null){
                request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                request.setAttribute(RequestParameter.SONG_AUTHORS, song.getAuthorList());
                request.setAttribute(RequestParameter.SONG_ALBUMS, song.getAlbumList());
                request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
                request.setAttribute(RequestParameter.SONG, song);
                page = JspPageName.ADMIN_EDIT_SONG;
            }else {
                page = JspPageName.ADMIN_HOME;
            }
        }else {
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
