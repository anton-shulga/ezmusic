package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.CreateSongService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.validator.CreateSongRequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long generatedId = null;
        String[] selectedAlbumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] selectedAuthorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
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
                generatedId = CreateSongService.create(song, ParameterParser.parseLongArray(selectedAlbumIds), ParameterParser.parseLongArray(selectedAuthorIds));
                ArrayList<Song> songList = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
            } catch (ServiceException e) {
                throw new CommandException("Creating song error", e);
            }
        }
        if(generatedId != null){
            return JspPageName.ADMIN_ALL_SONGS;
        }else {
            return JspPageName.ADMIN_EDIT_SONG;
        }
    }
}
