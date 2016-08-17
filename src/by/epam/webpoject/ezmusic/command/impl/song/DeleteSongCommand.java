package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.song.DeleteSongService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.validator.DeleteSongRequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String songId = request.getParameter(RequestParameter.SONG_ID);
        boolean isValidRequest = DeleteSongRequestValidator.validate(songId);
        if(isValidRequest){
            try {
                DeleteSongService.delete(Long.parseLong(songId));
                ArrayList<Song> songList = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                return JspPageName.ADMIN_ALL_SONGS;
            } catch (ServiceException e) {
                throw new CommandException("Deleting song error", e);
            }
        }
        return JspPageName.ERROR;
    }
}
