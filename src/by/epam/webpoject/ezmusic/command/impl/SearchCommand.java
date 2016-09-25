package by.epam.webpoject.ezmusic.command.impl;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySearchRequestService;
import by.epam.webpoject.ezmusic.service.author.FIndAuthorsBySearchRequestService;
import by.epam.webpoject.ezmusic.service.song.FindSongsBySearchRequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 15.09.2016.
 */
public class SearchCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String searchRequest = request.getParameter(RequestParameter.SEARCH_REQUEST);

        try {
            ArrayList<Album> albumList = FindAlbumsBySearchRequestService.find(searchRequest);
            ArrayList<Author> authorList = FIndAuthorsBySearchRequestService.find(searchRequest);
            ArrayList<Song> songList = FindSongsBySearchRequestService.find(searchRequest);
            if(albumList.isEmpty() && authorList.isEmpty() && songList.isEmpty()){
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.NOT_FOUND);
                page = JspPageName.USER_SEARCH_RESULT;
            }else {
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                page = JspPageName.USER_SEARCH_RESULT;
            }
        } catch (ServiceException e) {
            throw new CommandException("Search command exception", e);
        }
        return page;
    }
}
