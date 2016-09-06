package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.service.song.UpdateSongService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class UpdateSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String songId = request.getParameter(RequestParameter.SONG_ID);
        String[] albumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String name = request.getParameter(RequestParameter.SONG_NAME);
        String year = request.getParameter(RequestParameter.SONG_YEAR);
        String filePath = request.getParameter(RequestParameter.SONG_FILE_PATH);
        String publicationDate = request.getParameter(RequestParameter.SONG_PUBLICATION_DATE);
        String cost = request.getParameter(RequestParameter.SONG_COST);

        Song song = null;

        boolean isValidRequest = SongParametersValidator.validateUpdateParameters(albumIds, authorIds, songId, name, year, filePath, publicationDate, cost);
        if(isValidRequest) {
            song = new Song();
            song.setSongId(Long.parseLong(songId));
            song.setName(name);
            song.setYear(Integer.parseInt(year));
            song.setFilePath(filePath);
            song.setPublicationDate(Date.valueOf(publicationDate));
            song.setCost(Double.parseDouble(cost));

            if(albumIds != null) {
                Album album = null;
                ArrayList<Album> albums = new ArrayList<>();
                for (Long albumId : ParameterParser.parseLongArray(albumIds)) {
                    album = new Album();
                    album.setAlbumId(albumId);
                    albums.add(album);
                }
                song.setAlbumList(albums);
            }
            if(authorIds != null) {
                Author author = null;
                ArrayList<Author> authors = new ArrayList<>();
                for (Long authorId : ParameterParser.parseLongArray(authorIds)) {
                    author = new Author();
                    author.setAuthorId(authorId);
                    authors.add(author);
                }
                song.setAuthorList(authors);
            }
            try {
                UpdateSongService.update(song);
                ArrayList<Song> songList = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                request.setAttribute(RequestParameter.MESSAGE, "Successfully updated song");
                page = JspPageName.ADMIN_ALL_SONGS;
            } catch (ServiceException e) {
                throw new CommandException("Update song command exception", e);
            }
        }
        else {
            request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check input parameters");
            page = JspPageName.ADMIN_HOME;
        }
        return page;
    }


}
