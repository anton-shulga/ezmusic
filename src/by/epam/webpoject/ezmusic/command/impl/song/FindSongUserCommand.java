package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
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

                if(song != null) {
                    request.setAttribute(RequestParameter.SONG, song);
                    page = JspPageName.USER_SONG;
                }else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                    page = JspPageName.USER_HOME;
                }
            } catch (ServiceException e) {
                throw new CommandException("Find song user command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.USER_HOME;
        }
        return page;
    }
}
