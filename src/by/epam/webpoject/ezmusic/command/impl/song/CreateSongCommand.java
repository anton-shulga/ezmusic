package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.song.CreateSongService;
import by.epam.webpoject.ezmusic.validator.CreateSongRequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean isCreated = false;
        String name = request.getParameter(RequestParameter.SONG_NAME);
        String year = request.getParameter(RequestParameter.SONG_YEAR);
        String filePath = request.getParameter(RequestParameter.SONG_FILE_PATH);
        String publicationDate = request.getParameter(RequestParameter.SONG_PUBLICATION_DATE);
        String cost = request.getParameter(RequestParameter.SONG_COST);
        boolean isValidRequest = CreateSongRequestValidator.validate(name, year, filePath, publicationDate, cost);
        if(isValidRequest) {
            Song song = new Song();
            song.setName(name);
            song.setYear(Integer.parseInt(year));
            song.setFilePath(filePath);
            song.setPublicationDate(Date.valueOf(publicationDate));
            song.setCost(Double.parseDouble(cost));
            try {
                isCreated = CreateSongService.create(song);
            } catch (ServiceException e) {
                throw new CommandException("Creating song error", e);
            }
        }
        if(isCreated){
            return JspPageName.ADMIN_ALL_SONGS;
        }else {
            return JspPageName.ADMIN_ALL_SONGS;
        }
    }
}
