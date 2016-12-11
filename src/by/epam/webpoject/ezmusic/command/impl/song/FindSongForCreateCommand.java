package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.MessageKey;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.*;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.author.FindAllAuthorsService;
import by.epam.webpoject.ezmusic.service.song.FindAllGenresService;
import by.epam.webpoject.ezmusic.service.song.FindAllRewardsService;
import by.epam.webpoject.ezmusic.service.song.FindAllTagService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 17.08.2016.
 */
public class FindSongForCreateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        ArrayList<Album> albumList = null;
        ArrayList<Author> authorList = null;
        ArrayList<Tag> tagList = null;
        ArrayList<Reward> rewardList = null;
        ArrayList<Genre> genreList = null;

        try {
            albumList = FindAllAlbumsService.find();
            authorList = FindAllAuthorsService.find();
            tagList = FindAllTagService.find();
            rewardList = FindAllRewardsService.find();
            genreList = FindAllGenresService.findAll();


            if (albumList != null && authorList != null) {
                request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                request.setAttribute(RequestParameter.ALL_AUTHORS, authorList);
                request.setAttribute("all_tags", tagList);
                request.setAttribute("all_rewards", rewardList);
                request.setAttribute("all_genres", genreList);
                page = JspPageName.ADMIN_EDIT_SONG;
            } else {
                request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                page = JspPageName.ADMIN_HOME;
            }
        } catch (ServiceException e) {
            throw new CommandException("Find song for create command exception", e);
        }

        return page;
    }
}
