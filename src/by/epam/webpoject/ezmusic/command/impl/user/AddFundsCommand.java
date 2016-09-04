package by.epam.webpoject.ezmusic.command.impl.user;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.user.UpdateUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 03.09.2016.
 */
public class AddFundsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String output = null;
        String moneyAmount = request.getParameter(RequestParameter.MONEY_AMOUNT);
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        user.setBalance(user.getBalance()+ ParameterParser.parseLong(moneyAmount));
        try {
            UpdateUserService.update(user);
            request.getSession().setAttribute(RequestParameter.USER, user);
        } catch (ServiceException e) {
            throw new CommandException("Add funds command exception", e);
        }
        return String.valueOf(user.getBalance());
    }
}
