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
        ArrayList<Album> albumList = null;
        ArrayList<Author> authorList = null;
            try {
                albumList = FindAllAlbumsService.find();
                authorList = FindAllAuthorsService.find();
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                return JspPageName.ADMIN_EDIT_SONG;
            } catch (ServiceException e) {
                throw new CommandException("Finding song error", e);
            }
    }
}
