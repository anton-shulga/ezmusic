package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 17.08.2016.
 */
public class FindSongForCreateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Album> allAlbums = null;
        ArrayList<Author> allAuthors = null;

        try {
            allAlbums = FindAllAlbumsService.find();
            allAuthors = FindAllAuthorsService.find();
            if (allAlbums != null && allAuthors != null) {
                request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
                request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
                page = JspPageName.ADMIN_EDIT_SONG;
            } else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find songs command exception", e);
        }

        return page;
    }
}
