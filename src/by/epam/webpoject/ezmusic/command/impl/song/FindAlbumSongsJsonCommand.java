package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAlbumIdService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;
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

        Set<Song> albumSongs = null;

        String[] albumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS + "[]");

        boolean isValidRequest = SongParametersValidator.validateFindJsonParameters(albumIds);
        if(isValidRequest) {
            try {
                albumSongs = new HashSet<>();

                if (albumIds == null) {
                    albumSongs.addAll(FindAllSongsService.find());
                } else {
                    Long[] longAlbumIds = ParameterParser.parseLongArray(albumIds);
                    for (Long albumId : longAlbumIds) {
                        albumSongs.addAll(FindSongsByAlbumIdService.find(albumId));
                    }
                }
            } catch (ServiceException e) {
                throw new CommandException("Find album songs json command exception", e);
            }
        }

        return new Gson().toJson(albumSongs);
    }
}
