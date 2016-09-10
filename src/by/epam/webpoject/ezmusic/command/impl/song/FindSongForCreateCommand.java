package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
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
        ArrayList<Album> albumList = null;
        ArrayList<Author> authorList = null;

        try {
            albumList = FindAllAlbumsService.find();
            authorList = FindAllAuthorsService.find();

            if (albumList != null && authorList != null) {
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                page = JspPageName.ADMIN_EDIT_SONG;
            } else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find song for create command exception", e);
        }

        return page;
    }
}
