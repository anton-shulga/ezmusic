package by.epam.webpoject.ezmusic.command.impl.album;

import by.epam.webpoject.ezmusic.command.Command;
import by.epam.webpoject.ezmusic.constant.RequestParameter;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.CommandException;
import by.epam.webpoject.ezmusic.exception.ServiceException;
import by.epam.webpoject.ezmusic.parser.ParameterParser;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsByAuthorIdService;
import by.epam.webpoject.ezmusic.service.album.FindAllAlbumsService;
import by.epam.webpoject.ezmusic.validator.AlbumParametersValidator;
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

        Set<Album> authorAlbums = null;

        String[] authorIds = request.getParameterValues(RequestParameter.SELECTED_AUTHORS + "[]");

        boolean isValidRequest = AlbumParametersValidator.validateFindJsonParameters(authorIds);
        if(isValidRequest) {
            authorAlbums = new HashSet<>();

            try {
                if(authorIds == null){
                    authorAlbums.addAll(FindAllAlbumsService.find());
                }else {
                    Long[] longAuthorIds = ParameterParser.parseLongArray(authorIds);

                    for (Long authorId : longAuthorIds) {
                        authorAlbums.addAll(FindAlbumsByAuthorIdService.find(authorId));
                    }
                }
            } catch (ServiceException e) {
                throw new CommandException("Find author albums json command exception", e);
            }
        }

        return new Gson().toJson(authorAlbums);
    }
}
