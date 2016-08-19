package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 18.08.2016.
 */
public class FindAllAlbumsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Album> allAlbums = null;
        try {
            allAlbums = FindAllAlbumsService.find();
        } catch (ServiceException e) {
            throw new CommandException("Finding albums error", e);
        }
        if (allAlbums != null) {
            request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
            page = JspPageName.ADMING_ALL_ALBUMS;
        }else {
            page = JspPageName.ERROR;
        }
        return page;
    }
}
