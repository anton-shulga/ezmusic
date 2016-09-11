package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.CreateAlbumService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateAlbumCommand implements Command{

    private boolean f5Pressed(String sessionToken, String requestToken){
        if(sessionToken != null){
            if(requestToken != null){
                return sessionToken.equals(requestToken);
            }
        }else {
            return false;
        }
        return false;
    }
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Long generatedId = null;
        Album album = null;

        String[] selectedSongIds = request.getParameterValues(RequestParameter.SELECTED_SONGS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String name = request.getParameter(RequestParameter.ALBUM_NAME);
        String year = request.getParameter(RequestParameter.ALBUM_YEAR);
        String filePath = request.getParameter(RequestParameter.ALBUM_IMAGE_FILE_PATH);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);


        if(!f5Pressed(sessionToken, requestToken)) {
            request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
            boolean isValidRequest = AlbumParametersValidator.validateCreateParameters(selectedSongIds, authorIds, name, year, filePath);
            if (isValidRequest) {
                album = new Album();
                album.setName(name);
                album.setYear(ParameterParser.parseInt(year));
                album.setImageFilePath(filePath);
                try {
                    generatedId = CreateAlbumService.create(album, ParameterParser.parseLongArray(selectedSongIds), ParameterParser.parseLongArray(authorIds));

                    if (generatedId != null) {
                        ArrayList<Album> albumList = FindAllAlbumsService.find();
                        request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                        request.setAttribute(RequestParameter.MESSAGE, "Successfully created album " + name);
                        page = JspPageName.ADMIN_ALL_ALBUMS;
                    } else {
                        request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                        page = JspPageName.ADMIN_EDIT_ALBUM;
                    }
                } catch (ServiceException e) {
                    throw new CommandException("Create album command exception", e);
                }
            } else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
                page = JspPageName.ADMIN_EDIT_ALBUM;
            }
        }else {
            page = JspPageName.ERROR;
        }

        return page;
    }
}
