package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumByIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsByAlbumIdService;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAlbumIdService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 15.09.2016.
 */
public class FindAlbumUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String albumId = request.getParameter(RequestParameter.ALBUM_ID);

        boolean isValidRequest = AlbumParametersValidator.validateFindParameters(albumId);
        if(isValidRequest){
            try {
                Album album = FindAlbumByIdService.find(ParameterParser.parseLong(albumId));
                ArrayList<Author> albumAuthors = null;
                ArrayList<Song> albumSongs = null;
                if(album != null){
                    albumAuthors = FindAuthorsByAlbumIdService.find(ParameterParser.parseLong(albumId));
                    albumSongs = FindSongsByAlbumIdService.find(ParameterParser.parseLong(albumId));
                }
                request.setAttribute(RequestParameter.ALBUM, album);
                request.setAttribute(RequestParameter.ALBUM_SONGS, albumSongs);
                request.setAttribute(RequestParameter.ALBUM_AUTHORS, albumAuthors);
                page = JspPageName.USER_ALBUM;
            } catch (ServiceException e) {
                throw new CommandException("Find album user command exception", e);
            }
        }
        return page;
    }
}
