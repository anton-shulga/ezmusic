package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.*;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorTypesService;
import by.epam.webpoject.ezmusic.service.author.FindAllLabelsService;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsByAuthorIdService;
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
        ArrayList<Song> songList = null;
        ArrayList<Album> albumList = null;
        ArrayList<Song> authorSongs = null;
        ArrayList<Album> authorAlbums = null;
        ArrayList<Label> labelList = null;
        ArrayList<AuthorType> authorTypeList = null;

        String authorId = request.getParameter(RequestParameter.AUTHOR_ID);

        boolean isValidRequest = AuthorParametersValidator.validateFindParameters(authorId);
        if (isValidRequest) {
            try {
                Long longAuthorId = ParameterParser.parseLong(authorId);
                author = FindAuthorByIdService.find(longAuthorId);

                if (author != null) {
                    songList = FindAllSongsService.find();
                    albumList = FindAllAlbumsService.find();
                    labelList = FindAllLabelsService.find();
                    authorTypeList = FindAllAuthorTypesService.find();
                    authorSongs = FindSongsByAuthorIdService.find(longAuthorId);
                    authorAlbums = FindAlbumsByAuthorIdService.find(longAuthorId);
                    request.setAttribute(RequestParameter.AUTHOR, author);
                    request.setAttribute(RequestParameter.AUTHOR_SONGS, authorSongs);
                    request.setAttribute(RequestParameter.AUTHOR_ALBUMS, authorAlbums);
                    request.setAttribute(RequestParameter.ALL_SONGS, songList);
                    request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                    request.setAttribute("all_author_types", authorTypeList);
                    request.setAttribute("all_labels", labelList);
                    page = JspPageName.ADMIN_EDIT_AUTHOR;
                } else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                    page = JspPageName.ADMIN_HOME;
                }
            } catch (ServiceException e) {
                throw new CommandException("Find author for update command exception", e);
            }
        } else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
