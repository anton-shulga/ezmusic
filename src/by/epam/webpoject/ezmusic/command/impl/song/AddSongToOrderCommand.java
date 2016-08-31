package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.order.GetOrderSongsNumberByUserIdService;
import by.epam.webpoject.ezmusic.service.song.AddSongToOrderService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 30.08.2016.
 */
public class AddSongToOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String songId = request.getParameter(RequestParameter.SONG_ID);
        Long orderSongsNumber = null;
        boolean isValidRequest = SongParametersValidator.validateFindParameters(songId);

        if(isValidRequest){
            User user = (User) request.getSession().getAttribute(RequestParameter.USER);
            try {
                AddSongToOrderService.add(user.getUserId(), ParameterParser.parseLong(songId));
                orderSongsNumber = GetOrderSongsNumberByUserIdService.get(user.getUserId());
                ArrayList<Song> allSongs = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, allSongs);
                request.getSession().setAttribute(RequestParameter.ORDER_SONGS_NUMBER, orderSongsNumber);
            }catch (ServiceException e) {
              throw new CommandException("Add song to order command exception", e);
            }
        }
        return orderSongsNumber.toString();
    }

}
