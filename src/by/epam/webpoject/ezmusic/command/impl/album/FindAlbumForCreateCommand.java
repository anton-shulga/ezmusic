package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
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
        ArrayList<Author> allAuthors = null;
        ArrayList<Song> allSongs = null;
        try{
            allAuthors = FindAllAuthorsService.find();
            allSongs = FindAllSongsService.find();
        } catch (ServiceException e) {
            throw new CommandException("Finding album error", e);
        }
        if(allAuthors != null && allSongs != null){
            request.setAttribute(RequestParameter.ALL_AUTHORS, allAuthors);
            request.setAttribute(RequestParameter.ALL_SONGS, allSongs);
            return JspPageName.ADMIN_EDIT_ALBUM;
        }else {
            return JspPageName.ERROR;
        }
    }
}
