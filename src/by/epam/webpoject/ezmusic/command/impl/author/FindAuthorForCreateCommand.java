package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAuthorForCreateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Song> allSongs = null;
        ArrayList<Album> allAlbums = null;
        try {
            allSongs = FindAllSongsService.find();
            allAlbums = FindAllAlbumsService.find();
            if(allSongs != null && allAlbums != null){
                request.setAttribute(RequestParameter.ALL_SONGS, allSongs);
                request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
                page = JspPageName.ADMIN_EDIT_AUTHOR;
            }else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find author command exception", e);
        }

        return page;
    }
}
