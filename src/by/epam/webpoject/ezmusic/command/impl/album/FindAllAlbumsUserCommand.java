package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 30.08.2016.
 */
public class FindAllAlbumsUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        ArrayList<Album> albumList = null;

        try {
            albumList = FindAllAlbumsService.find();
            if (albumList != null) {
                if(albumList.isEmpty()){
                    request.setAttribute(RequestParameter.MESSAGE, "Not found any albums");
                }
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                page = JspPageName.USER_ALL_ALBUMS;
            }else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                page = JspPageName.USER_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find all albums user command exception", e);
        }
        return page;
    }
}
