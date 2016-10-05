package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsByAuthorIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorByIdService;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAuthorIdService;
import by.epam.webpoject.ezmusic.validator.AuthorParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 15.09.2016.
 */
public class FindAuthorUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);

        boolean isValidRequest = AuthorParametersValidator.validateFindParameters(authorId);
        if (isValidRequest) {
            try {
                Author author = FindAuthorByIdService.find(ParameterParser.parseLong(authorId));
                ArrayList<Album> authorAlbums = null;
                ArrayList<Song> authorSongs = null;
                if (author != null) {
                    authorAlbums = FindAlbumsByAuthorIdService.find(ParameterParser.parseLong(authorId));
                    authorSongs = FindSongsByAuthorIdService.find(ParameterParser.parseLong(authorId));
                }
                request.setAttribute(RequestParameter.AUTHOR, author);
                request.setAttribute(RequestParameter.AUTHOR_ALBUMS, authorAlbums);
                request.setAttribute(RequestParameter.AUTHOR_SONGS, authorSongs);
                page = JspPageName.USER_AUTHOR;
            } catch (ServiceException e) {
                throw new CommandException("Find album user command exception", e);
            }
        }
        return page;
    }
}
