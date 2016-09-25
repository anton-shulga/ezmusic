package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.order.FindCartByUserIdService;
import by.epam.webpoject.ezmusic.service.song.DeleteSongFromCartService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 02.09.2016.
 */
public class DeleteSongFromCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;

        String  songId = request.getParameter(RequestParameter.SONG_ID);
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        Order cart = (Order) request.getSession().getAttribute(RequestParameter.CART);

        boolean isValidRequest = SongParametersValidator.validateDeleteFromCartParameters(songId, cart);
        if(isValidRequest) {
            try {
                DeleteSongFromCartService.delete(ParameterParser.parseLong(songId), cart);

                cart = FindCartByUserIdService.find(user.getUserId());
                request.getSession().setAttribute(RequestParameter.CART, cart);
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.DELETED);
                page = JspPageName.USER_CART;
            } catch (ServiceException e) {
                throw new CommandException("Delete song from cart command exception", e);
            }
        }else {
            request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
            page = JspPageName.USER_HOME;
        }
        return page;
    }
}
