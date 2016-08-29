package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.FindSongsByAuthorIdService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAuthorSongsJsonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS + "[]");
        Set<Song> authorSongs = new HashSet<>();
        boolean isValidRequest = SongParametersValidator.validateFindJsonParameters(authorIds);
        if(isValidRequest) {
            Long[] longAuthorIds = ParameterParser.parseLongArray(authorIds);
            try {
                for (Long authorId : longAuthorIds) {
                    authorSongs.addAll(FindSongsByAuthorIdService.find(authorId));
                }
            } catch (ServiceException e) {
                throw new CommandException("Find author songs command exception", e);
            }
        }
        return new Gson().toJson(authorSongs);
    }
}
