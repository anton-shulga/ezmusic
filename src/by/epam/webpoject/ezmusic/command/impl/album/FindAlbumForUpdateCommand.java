package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumByIdService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsByAlbumIdService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAlbumIdService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindAlbumForUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Album album = null;
        ArrayList<Author> authorList = null;
        ArrayList<Song> songList = null;
        ArrayList<Author> albumAuthors = null;
        ArrayList<Song> albumSongs = null;

        String albumId = request.getParameter(RequestParameter.ALBUM_ID);

        boolean isValidRequest = AlbumParametersValidator.validateFindParameters(albumId);
        if(isValidRequest) {
            try {
                Long longAlbumId = ParameterParser.parseLong(albumId);
                album = FindAlbumByIdService.find(longAlbumId);

                if(album != null) {
                    authorList = FindAllAuthorsService.find();
                    songList = FindAllSongsService.find();
                    albumSongs = FindSongsByAlbumIdService.find(longAlbumId);
                    albumAuthors = FindAuthorsByAlbumIdService.find(longAlbumId);
                    request.setAttribute(RequestParameter.ALBUM, album);
                    request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                    request.setAttribute(RequestParameter.ALL_SONGS, songList);
                    request.setAttribute(RequestParameter.ALBUM_AUTHORS, albumAuthors);
                    request.setAttribute(RequestParameter.ALBUM_SONGS, albumSongs);
                    page = JspPageName.ADMIN_EDIT_ALBUM;
                }else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                    page = JspPageName.ADMIN_HOME;
                }
            } catch (ServiceException e) {
                throw new CommandException("Find album for update command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.ADMIN_HOME;
        }
        return page;

    }
}
