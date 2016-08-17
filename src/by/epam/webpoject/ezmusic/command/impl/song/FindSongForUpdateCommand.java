package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumBySongIdService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.service.song.FindSongByIdService;
import by.epam.webpoject.ezmusic.validator.FindSongReuqestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class FindSongForUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String songId = request.getParameter(RequestParameter.SONG_ID);
        Song song = null;
        Album album = null;
        ArrayList<Album> albumList = null;
        boolean isValidRequest = FindSongReuqestValidator.validate(songId);
        if(isValidRequest){
            try {
                song = FindSongByIdService.find(Long.parseLong(songId));
                album = FindAlbumBySongIdService.find(song.getSongId());
                albumList = FindAllAlbumsService.find();
                if(song != null  && albumList != null){
                    request.setAttribute(RequestParameter.ALBUM, album);
                    request.setAttribute(RequestParameter.ALL_ALBUMS, albumList);
                    request.setAttribute(RequestParameter.SONG, song);
                    return JspPageName.ADMIN_EDIT_SONG;
                }
            } catch (ServiceException e) {
                throw new CommandException("Finding song error", e);
            }
        }
        return JspPageName.ERROR;
    }
}
