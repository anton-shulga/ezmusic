package by.epam.webpoject.ezmusic.command.impl.author;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.*;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorTypesService;
import by.epam.webpoject.ezmusic.service.author.FindAllLabelsService;
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
        ArrayList<Song> songList = null;
        ArrayList<Album> albumList = null;
        ArrayList<Label> labelList = null;
        ArrayList<AuthorType> authorTypeList = null;

        try {
            songList = FindAllSongsService.find();
            albumList = FindAllAlbumsService.find();
            labelList = FindAllLabelsService.find();
            authorTypeList = FindAllAuthorTypesService.find();

            if (songList != null && albumList != null) {
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                request.setAttribute("all_author_types", authorTypeList);
                request.setAttribute("all_labels", labelList);
                page = JspPageName.ADMIN_EDIT_AUTHOR;
            } else {
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find author for create command exception", e);
        }

        return page;
    }
}
