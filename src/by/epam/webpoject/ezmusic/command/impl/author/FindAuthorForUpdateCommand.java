package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumByAuthorIdService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorByIdService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAuthorIdService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAuthorForUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Author author = null;
        ArrayList<Song> allSongs = null;
        ArrayList<Album> allAlbums = null;
        ArrayList<Song> authorSongs = null;
        ArrayList<Album> authorAlbums = null;
        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);
        boolean isValidRequest = AuthorParametersValidator.validateFindParameters(authorId);
        if(isValidRequest) {
            try {
                author = FindAuthorByIdService.find(ParameterParser.parseLong(authorId));
                allSongs = FindAllSongsService.find();
                allAlbums = FindAllAlbumsService.find();
                authorSongs = FindSongsByAuthorIdService.find(ParameterParser.parseLong(authorId));
                authorAlbums = FindAlbumByAuthorIdService.find(ParameterParser.parseLong(authorId));
            } catch (ServiceException e) {
                throw new CommandException("Find author command exception", e);
            }
            if (author != null && allAlbums != null && allSongs != null && authorAlbums != null && authorSongs != null) {
                request.setAttribute(RequestParameter.AUTHOR_SONGS, authorSongs);
                request.setAttribute(RequestParameter.AUTHOR_ALBUMS, authorAlbums);
                request.setAttribute(RequestParameter.AUTHOR, author);
                request.setAttribute(RequestParameter.ALL_SONGS, allSongs);
                request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
                page = JspPageName.ADMIN_EDIT_AUTHOR;
            } else {
                page = JspPageName.ADMIN_HOME;
            }
        }else{
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
