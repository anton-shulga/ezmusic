package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.DeleteSongService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.IsOrderedSongService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class DeleteSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);

        boolean isValidRequest = SongParametersValidator.validateDeleteParameters(songId);
        if(isValidRequest){
            try {
                if(!IsOrderedSongService.isOrdered(ParameterParser.parseLong(songId))) {
                    DeleteSongService.delete(Long.parseLong(songId));
                    ArrayList<Song> songList = FindAllSongsService.find();
                    request.setAttribute(RequestParameter.ALL_SONGS, songList);
                    request.setAttribute(RequestParameter.MESSAGE, "Successfully deleted song");
                    page = JspPageName.ADMIN_ALL_SONGS;
                }else {
                    ArrayList<Song> songList = FindAllSongsService.find();
                    request.setAttribute(RequestParameter.ALL_SONGS, songList);
                    request.setAttribute(RequestParameter.MESSAGE, "You can't delete this song");
                    page = JspPageName.ADMIN_ALL_SONGS;
                }
            } catch (ServiceException e) {
                throw new CommandException("Delete song command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }
}
