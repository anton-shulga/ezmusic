package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 12.08.2016.
 */
public class FindAllSongsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        ArrayList<Song> songList = null;

        try {
            songList = FindAllSongsService.find();

            if (songList == null || songList.isEmpty()) {
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.NOT_FOUND);
                page = JspPageName.ADMIN_ALL_SONGS;
            } else {
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                page = JspPageName.ADMIN_ALL_SONGS;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find all songs command exception", e);
        }
        return page;
    }
}
