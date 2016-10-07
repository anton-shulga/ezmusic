package by.epam.webpoject.ezmusic.command.impl.song;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.*;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.util.ParameterParser;
import by.epam.webpoject.ezmusic.service.song.CreateSongService;
import by.epam.webpoject.ezmusic.service.song.FindAllSongsService;
import by.epam.webpoject.ezmusic.validator.SongParametersValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

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
        String cost = request.getParameter(RequestParameter.SONG_COST);

        String sessionToken = (String) request.getSession().getAttribute(RequestParameter.TOKEN);
        String requestToken = request.getParameter(RequestParameter.TOKEN);

        try {
            if (!f5Pressed(sessionToken, requestToken)) {
                request.getSession().setAttribute(RequestParameter.TOKEN, requestToken);
                boolean isValidRequest = SongParametersValidator.validateCreateParameters(authorIds, albumIds, name, year, cost);

                if (isValidRequest) {
                    song = new Song();
                    song.setName(name);
                    song.setYear(Integer.parseInt(year));
                    song.setCost(Double.parseDouble(cost));

                    try {
                        if (request.getPart(RequestParameter.SONG_FILE_PATH).getInputStream().available() != 0) {
                            String filePath = loadImage(request);
                            if (filePath != null) {
                                song.setFilePath(filePath);
                            } else {
                                song.setFilePath(FilePath.DEFAULT_SONG_FILE);
                            }
                        } else {
                            song.setFilePath(FilePath.DEFAULT_SONG_FILE);
                        }
                    } catch (IOException | ServletException e) {
                        throw new CommandException("Create song command exception", e);
                    }


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
                        request.setAttribute(RequestParameter.MESSAGE, MessageKey.CREATED);
                        page = JspPageName.ADMIN_ALL_SONGS;
                    } else {
                        request.setAttribute(RequestParameter.MESSAGE, MessageKey.OOPS);
                        page = JspPageName.ADMIN_HOME;
                    }

                } else {
                    request.setAttribute(RequestParameter.MESSAGE, MessageKey.INPUT);
                    page = JspPageName.ADMIN_EDIT_SONG;
                }
            } else {
                ArrayList<Song> songList = FindAllSongsService.find();
                request.setAttribute(RequestParameter.ALL_SONGS, songList);
                page = JspPageName.ADMIN_ALL_SONGS;
            }
        } catch (ServiceException e) {
            throw new CommandException("Create song command exception", e);
        }
        return page;
    }


    private String loadImage(HttpServletRequest request) throws CommandException {
        String appPath = request.getServletContext().getRealPath(File.separator);
        String songFileDirectory = request.getServletContext().getInitParameter(ContextParameter.SONG_FILE_DIRECTORY);
        String filePath = appPath + File.separator + songFileDirectory;

        try {
            if (!Files.exists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }
        } catch (IOException e) {
            throw new CommandException("Can't create directory for album image", e);
        }

        String songName = Double.toString(new Date().getTime()) + FileExtension.MP3;
        File file = new File(filePath + File.separator + songName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new CommandException("Can't load image", e);
        }

        try (
                InputStream inputStream = request.getPart(RequestParameter.SONG_FILE_PATH).getInputStream();
                FileOutputStream outputStream = new FileOutputStream(file)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (ServletException | IOException e) {
            throw new CommandException("Can't copy image", e);
        }

        return songFileDirectory + File.separator + songName;

    }

    private boolean f5Pressed(String sessionToken, String requestToken) {
        if (sessionToken != null) {
            if (requestToken != null) {
                return sessionToken.equals(requestToken);
            }
        } else {
            return false;
        }
        return false;
    }
}
