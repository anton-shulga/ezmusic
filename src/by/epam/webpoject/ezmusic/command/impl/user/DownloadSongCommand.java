package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 13.09.2016.
 */
public class DownloadSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);

        boolean isValidRequest = SongParametersValidator.validateDeleteParameters(songId);
        return page;
    }
}
