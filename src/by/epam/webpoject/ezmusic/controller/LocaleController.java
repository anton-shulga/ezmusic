package by.epam.webpoject.ezmusic.controller;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.command.CommandManager;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.exception.command.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Антон on 17.08.2016.
 */
public class LocaleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        try {
            Command command = CommandManager.getCommand(commandName);
            command.execute(req);
        } catch (CommandException e) {
            resp.sendRedirect(JspPageName.ERROR);
        }
    }
}
