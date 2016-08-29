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
import java.io.PrintWriter;

/**
 * Created by Антон on 02.08.2016.
 */
public class LoginAvailabilityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String commandName = req.getParameter(RequestParameter.COMMAND);
        String message;
        try {
            Command command = CommandManager.getCommand(commandName);
            message = command.execute(req);
            out.println(message);
        } catch (CommandException e) {
            resp.sendRedirect(JspPageName.ERROR);
        }
    }
}
