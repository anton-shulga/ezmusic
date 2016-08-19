package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.FindSongByAlbumIdService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAlbumSongsJsonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long[] albumIds = ParameterParser.parseLongArray(request.getParameterValues(RequestParameter.SELECTED_ALBUMS + "[]"));
        Set<Song> albumSongs = new HashSet<>();
        try {
            for (Long albumId: albumIds) {
                albumSongs.addAll(FindSongByAlbumIdService.find(albumId));
            }
        }catch (ServiceException e) {
            throw new CommandException("Finding author albums error", e);
        }
        return new Gson().toJson(albumSongs);
    }
}
