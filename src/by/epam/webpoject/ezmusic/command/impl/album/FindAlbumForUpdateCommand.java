package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumByIdService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorByAlbumIdService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.FindSongByAlbumIdService;

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
        ArrayList<Author> allAuthors = null;
        ArrayList<Song> allSongs = null;
        ArrayList<Author> albumAuthors = null;
        ArrayList<Song> albumSongs = null;
        Long albumId = ParameterParser.parseLong(request.getParameter(RequestParameter.ALBUM_ID));
        try {
            album = FindAlbumByIdService.find(albumId);
            allAuthors = FindAllAuthorsService.find();
            allSongs = FindAllSongsService.find();
            albumSongs = FindSongByAlbumIdService.find(albumId);
            albumAuthors = FindAuthorByAlbumIdService.find(albumId);
        } catch (ServiceException e) {
            throw new CommandException("Finding album error", e);
        }
        if(album != null && allAuthors != null && allSongs != null){
            request.setAttribute(RequestParameter.ALBUM, album);
            request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
            request.setAttribute(RequestParameter.ALL_SONGS, allSongs);
            request.setAttribute(RequestParameter.ALBUM_AUTHORS, albumAuthors);
            request.setAttribute(RequestParameter.ALBUM_SONGS, albumSongs);
            page = JspPageName.ADMIN_EDIT_ALBUM;
        }
        return page;

    }
}
