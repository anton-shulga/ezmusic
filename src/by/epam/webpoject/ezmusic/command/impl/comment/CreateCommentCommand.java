package by.epam.webpoject.ezmusic.command.impl.comment;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.comment.CreateCommentService;
import by.epam.webpoject.ezmusic.service.song.FindSongByIdService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateCommentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String songId = request.getParameter(RequestParameter.SONG_ID);
        String text = request.getParameter(RequestParameter.COMMENT_TEXT);
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setSongId(ParameterParser.parseLong(songId));
        comment.setText(text);
        comment.setRating(1);
        try {
            CreateCommentService.create(comment);
            Song song = FindSongByIdService.find(ParameterParser.parseLong(songId));
            request.setAttribute(RequestParameter.SONG, song);
            page = JspPageName.USER_SONG;
        } catch (ServiceException e) {
            throw new CommandException("Create comment command exception", e);
        }
        return page;
    }
}
