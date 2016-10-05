package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.order.FindCartByUserIdService;
import by.epam.webpoject.ezmusic.service.song.AddSongToOrderService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 30.08.2016.
 */
public class AddSongToOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String output = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        Order cart = (Order) request.getSession().getAttribute(RequestParameter.CART);

        boolean isValidRequest = SongParametersValidator.validateFindParameters(songId);
        if(isValidRequest){
            try {
                AddSongToOrderService.add(ParameterParser.parseLong(songId), cart);

                cart = FindCartByUserIdService.find(user.getUserId());
                request.getSession().setAttribute(RequestParameter.CART, cart);
                output = String.valueOf(cart.getSongList().size());
            }catch (ServiceException e) {
              throw new CommandException("Add song to order command exception", e);
            }
        }
        return new Gson().toJson(output);
    }

}
