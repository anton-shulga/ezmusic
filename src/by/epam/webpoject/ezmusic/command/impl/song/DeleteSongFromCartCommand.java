package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.order.FindCartByUserIdService;
import by.epam.webpoject.ezmusic.service.song.DeleteSongFromCartService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 02.09.2016.
 */
public class DeleteSongFromCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        String  songId = request.getParameter(RequestParameter.SONG_ID);
        try {
            DeleteSongFromCartService.delete(user.getUserId(), ParameterParser.parseLong(songId));
            Order cart = FindCartByUserIdService.find(user.getUserId());
            request.getSession().setAttribute(RequestParameter.CART, cart);
            page = JspPageName.USER_CART;
        } catch (ServiceException e) {
            throw new CommandException("Delete song from cart command exception", e);
        }
        return page;
    }
}
