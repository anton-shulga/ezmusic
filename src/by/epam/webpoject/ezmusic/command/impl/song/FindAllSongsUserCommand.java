package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 29.08.2016.
 */
public class FindAllSongsUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        ArrayList<Song> songList = null;
        try {
            songList = FindAllSongsService.find();
        } catch (ServiceException e) {
            throw new CommandException("Find songs command exception", e);
        }
        if(songList != null ){
            request.setAttribute(RequestParameter.ALL_SONGS, songList);
            return JspPageName.USER_ALL_SONGS;
        }else {
            return JspPageName.USER_HOME;
        }
    }
}
