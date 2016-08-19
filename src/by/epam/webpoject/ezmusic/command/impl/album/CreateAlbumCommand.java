package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.CreateAlbumService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long generatedId = null;
        String[] selectedSongIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String[] selectedAuthorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String name = request.getParameter(RequestParameter.ALBUM_NAME);
        String year = request.getParameter(RequestParameter.ALBUM_YEAR);
        String filePath = request.getParameter(RequestParameter.ALBUM_IMAGE_FILE_PATH);
        Album album = new Album();
        album.setName(name);
        album.setYear(ParameterParser.parseInt(year));
        album.setImageFilePath(filePath);
        try {
            generatedId = CreateAlbumService.create(album, ParameterParser.parseLongArray(selectedSongIds), ParameterParser.parseLongArray(selectedAuthorIds));
            ArrayList<Album> albumList = FindAllAlbumsService.find();
            request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
        } catch (ServiceException e) {
            throw new CommandException("Creating album error", e);
        }

        if(generatedId != null){
            return JspPageName.ADMING_ALL_ALBUMS;
        }else {
            return JspPageName.ADMIN_EDIT_ALBUM;
        }
    }
}
