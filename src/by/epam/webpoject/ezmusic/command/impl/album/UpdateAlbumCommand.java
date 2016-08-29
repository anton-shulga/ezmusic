package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.album.UpdateAlbumService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateAlbumCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String[] songIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String albumId = request.getParameter(RequestParameter.ALBUM_ID);
        String name = request.getParameter(RequestParameter.ALBUM_NAME);
        String year = request.getParameter(RequestParameter.ALBUM_YEAR);
        String filePath = request.getParameter(RequestParameter.ALBUM_IMAGE_FILE_PATH);
        boolean isValidRequest = AlbumParametersValidator.validateUpdateParameters(albumId, songIds, authorIds, name, year, filePath);
        if(isValidRequest) {
            Album album = new Album();
            album.setAlbumId(ParameterParser.parseLong(albumId));
            album.setName(name);
            album.setYear(ParameterParser.parseInt(year));
            album.setImageFilePath(filePath);
            try {
                UpdateAlbumService.update(album, ParameterParser.parseLongArray(songIds), ParameterParser.parseLongArray(authorIds));
                ArrayList<Album> albumList = FindAllAlbumsService.find();
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                page = JspPageName.ADMIN_ALL_ALBUMS;
            } catch (ServiceException e) {
                throw new CommandException("Update album command exception", e);
            }
        }else {
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
