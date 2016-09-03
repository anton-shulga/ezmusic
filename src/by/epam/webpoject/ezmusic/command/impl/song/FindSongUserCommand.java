package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.FindSongByIdService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 02.09.2016.
 */
public class FindSongUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Song song = null;
        String songId = request.getParameter(RequestParameter.SONG_ID);
        boolean isValidRequest = SongParametersValidator.validateFindParameters(songId);
        if(isValidRequest){
            try {
                song = FindSongByIdService.find(ParameterParser.parseLong(songId));
                request.setAttribute(RequestParameter.SONG, song);
                page = JspPageName.USER_SONG;
            } catch (ServiceException e) {
                throw new CommandException("Find song user command exception", e);
            }
        }else {
            page = JspPageName.USER_ALL_SONGS;
        }
        return page;
    }
}
