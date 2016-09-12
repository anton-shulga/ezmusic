package by.epam.webpoject.ezmusic.command.impl.comment;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Comment;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.comment.CreateCommentService;
import by.epam.webpoject.ezmusic.service.song.FindSongByIdService;
import by.epam.webpoject.ezmusic.validator.CommentParametersValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateCommentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Long generatedId = null;
        Comment comment = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);
        String text = request.getParameter(RequestParameter.COMMENT_TEXT);
        User user = (User) request.getSession().getAttribute(RequestParameter.USER);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);

        try {
            if (!f5Pressed(sessionToken, requestToken)) {
                request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
                boolean isValidRequest = CommentParametersValidator.validateCreateParameters(songId, text);
                if (isValidRequest) {
                    comment = new Comment();
                    comment.setUser(user);
                    comment.setSongId(ParameterParser.parseLong(songId));
                    comment.setText(text);

                    generatedId = CreateCommentService.create(comment);

                    if (generatedId != null) {
                        Song song = FindSongByIdService.find(ParameterParser.parseLong(songId));
                        request.setAttribute(RequestParameter.SONG, song);
                        page = JspPageName.USER_SONG;
                    }

                } else {
                    request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
                    page = JspPageName.USER_HOME;
                }
            }else {
                Song song = FindSongByIdService.find(ParameterParser.parseLong(songId));
                request.setAttribute(RequestParameter.SONG, song);
                page = JspPageName.USER_SONG;
            }
        }catch (ServiceException e){
            throw new CommandException("Create comment command exception", e);
        }

        return page;
    }

    private boolean f5Pressed(String sessionToken, String requestToken){
        if(sessionToken != null){
            if(requestToken != null){
                return sessionToken.equals(requestToken);
            }
        }else {
            return false;
        }
        return false;
    }
}
