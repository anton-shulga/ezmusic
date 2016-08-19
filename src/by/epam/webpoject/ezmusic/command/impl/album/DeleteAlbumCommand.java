package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.DeleteAlbumService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteAlbumCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String albumId = request.getParameter(RequestParameter.ALBUM_ID);
        String page = null;
        try {
            DeleteAlbumService.delete(ParameterParser.parseLong(albumId));
            ArrayList<Album> allAlbums = FindAllAlbumsService.find();
            request.setAttribute(RequestParameter.ALL_ALBUMS, allAlbums);
            page = JspPageName.ADMING_ALL_ALBUMS;
        } catch (ServiceException e) {
            throw new CommandException("Deleting album error", e);
        }
        return page;
    }
}
