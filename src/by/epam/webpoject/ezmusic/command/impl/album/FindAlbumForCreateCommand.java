package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 18.08.2016.
 */
public class FindAlbumForCreateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        ArrayList<Author> authorList = null;
        ArrayList<Song> songList = null;

        try {
            authorList = FindAllAuthorsService.find();
            songList = FindAllSongsService.find();

            if(authorList != null && songList != null) {
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                page = JspPageName.ADMIN_EDIT_ALBUM;
            }else {
                request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
            }
        } catch (ServiceException e) {
            throw new CommandException("Find album for create command exception", e);
        }

        return page;
    }
}
