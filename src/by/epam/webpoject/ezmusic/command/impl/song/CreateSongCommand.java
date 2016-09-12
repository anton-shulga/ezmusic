package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.JspPageName;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.CreateSongService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Антон on 10.08.2016.
 */
public class CreateSongCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        Long generatedId = null;
        Song song = null;

        String[] albumIds = request.getParameterValues(RequestParameter.SELECTED_ALBUMS);
        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS);
        String name = request.getParameter(RequestParameter.SONG_NAME);
        String year = request.getParameter(RequestParameter.SONG_YEAR);
        String filePath = request.getParameter(RequestParameter.SONG_FILE_PATH);
        String cost = request.getParameter(RequestParameter.SONG_COST);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);

        try {
            if (!f5Pressed(sessionToken, requestToken)) {
                request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
                boolean isValidRequest = SongParametersValidator.validateCreateParameters(authorIds, albumIds, name, year, filePath, cost);

                if (isValidRequest) {
                    song = new Song();
                    song.setName(name);
                    song.setYear(Integer.parseInt(year));
                    song.setFilePath(filePath);
                    song.setCost(Double.parseDouble(cost));

                    if (albumIds != null) {
                        Album album = null;
                        ArrayList<Album> albums = new ArrayList<>();
                        for (Long albumId : ParameterParser.parseLongArray(albumIds)) {
                            album = new Album();
                            album.setAlbumId(albumId);
                            albums.add(album);
                        }
                        song.setAlbumList(albums);
                    }

                    if (authorIds != null) {
                        Author author = null;
                        ArrayList<Author> authors = new ArrayList<>();
                        for (Long authorId : ParameterParser.parseLongArray(authorIds)) {
                            author = new Author();
                            author.setAuthorId(authorId);
                            authors.add(author);
                        }
                        song.setAuthorList(authors);
                    }

                    generatedId = CreateSongService.create(song);

                    if (generatedId != null) {
                        ArrayList<Song> songList = FindAllSongsService.find();
                        request.setAttribute(RequestParameter.ALL_SONGS, songList);
                        request.setAttribute(RequestParameter.MESSAGE, "Successfully created song " + name);
                        page = JspPageName.ADMIN_ALL_SONGS;
                    } else {
                        request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong");
                        page = JspPageName.ADMIN_HOME;
                    }

                } else {
                    request.setAttribute(RequestParameter.MESSAGE, "Oops! Something is wrong. Check the input data");
                    page = JspPageName.ADMIN_HOME;
                }
            }else {
                ArrayList<Song> songList = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                page = JspPageName.ADMIN_ALL_SONGS;
            }
        } catch (ServiceException e) {
            throw new CommandException("Create song command exception", e);
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
