package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.command.CommandException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumByAuthorIdService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Антон on 19.08.2016.
 */
public class FindAuthorAlbumsJsonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Long[] authorIds = ParameterParser.parseLongArray(request.getParameterValues(RequestParameter.SELECTED_AUTHORS + "[]"));
        Set<Album> authorAlbums = new HashSet<>();
        try {
            for (Long authorId: authorIds) {
                authorAlbums.addAll(FindAlbumByAuthorIdService.find(authorId));
            }
        }catch (ServiceException e) {
            throw new CommandException("Finding author albums error", e);
        }
        return new Gson().toJson(authorAlbums);
    }
}
