package by.epam.webpoject.ezmusic.command;

import by.epam.webpoject.ezmusic.command.impl.*;
import by.epam.webpoject.ezmusic.command.impl.album.*;
import by.epam.webpoject.ezmusic.command.impl.author.*;
import by.epam.webpoject.ezmusic.command.impl.comment.CreateCommentCommand;
import by.epam.webpoject.ezmusic.command.impl.song.*;
import by.epam.webpoject.ezmusic.command.impl.user.*;
import by.epam.webpoject.ezmusic.exception.command.CommandException;

import java.util.HashMap;

/**
 * Created by Антон on 15.07.2016.
 */
public class CommandManager {

    private static HashMap<CommandName, Command> availableCommands = new HashMap<>();

    static {
        availableCommands.put(CommandName.GO_HOME, new GoHomeCommand());
        availableCommands.put(CommandName.TO_LOGIN, new ToLoginCommand());
        availableCommands.put(CommandName.TO_REGISTER, new ToRegisterCommand());
        availableCommands.put(CommandName.LOGIN, new LoginCommand());
        availableCommands.put(CommandName.REGISTER, new RegisterUserCommand());
        availableCommands.put(CommandName.DELETE_USER, new DeleteUserCommand());
        availableCommands.put(CommandName.FIND_USER, new FindUserCommand());
        availableCommands.put(CommandName.UPDATE_USER, new UpdateUserCommand());
        availableCommands.put(CommandName.CHECK_LOGIN_AVAILABILITY, new CheckLoginAvailabilityCommand());
        availableCommands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        availableCommands.put(CommandName.FIND_ALL_SONGS_ADMIN, new FindAllSongsCommand());
        availableCommands.put(CommandName.LOGOUT, new LogoutCommand());
        availableCommands.put(CommandName.FIND_SONG_FOR_UPDATE, new FindSongForUpdateCommand());
        availableCommands.put(CommandName.FIND_SONG_FOR_CREATE, new FindSongForCreateCommand());
        availableCommands.put(CommandName.CREATE_SONG, new CreateSongCommand());
        availableCommands.put(CommandName.UPDATE_SONG, new UpdateSongCommand());
        availableCommands.put(CommandName.DELETE_SONG, new DeleteSongCommand());
        availableCommands.put(CommandName.CREATE_ALBUM, new CreateAlbumCommand());
        availableCommands.put(CommandName.UPDATE_ALBUM, new UpdateAlbumCommand());
        availableCommands.put(CommandName.DELETE_ALBUM, new DeleteAlbumCommand());
        availableCommands.put(CommandName.FIND_ALL_ALBUMS_ADMIN, new FindAllAlbumsCommand());
        availableCommands.put(CommandName.FIND_ALBUM_FOR_CREATE, new FindAlbumForCreateCommand());
        availableCommands.put(CommandName.FIND_ALBUM_FOR_UPDATE, new FindAlbumForUpdateCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_ALBUMS_JSON, new FindAuthorAlbumsJsonCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_SONGS_JSON, new FindAuthorSongsJsonCommand());
        availableCommands.put(CommandName.FIND_ALL_AUTHORS_ADMIN, new FindAllAuthorsCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_FOR_CREATE, new FindAuthorForCreateCommand());
        availableCommands.put(CommandName.FIND_ALBUM_SONGS_JSON, new FindAlbumSongsJsonCommand());
        availableCommands.put(CommandName.CREATE_AUTHOR, new CreateAuthorCommand());
        availableCommands.put(CommandName.UPDATE_AUTHOR, new UpdateAuthorCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_FOR_UPDATE, new FindAuthorForUpdateCommand());
        availableCommands.put(CommandName.DELETE_AUTHOR, new DeleteAuthorCommand());
        availableCommands.put(CommandName.FIND_ALL_SONGS_USER, new FindAllSongsUserCommand());
        availableCommands.put(CommandName.HOME_ADMIN, new HomeAdminCommand());
        availableCommands.put(CommandName.HOME_USER, new HomeUserCommand());
        availableCommands.put(CommandName.FIND_ALL_ALBUMS_USER, new FindAllAlbumsUserCommand());
        availableCommands.put(CommandName.FIND_ALL_AUTHORS_USER, new FindAllAuthorsUserCommand());
        availableCommands.put(CommandName.ADD_SONG_TO_ORDER, new AddSongToOrderCommand());
        availableCommands.put(CommandName.FIND_CART_USER, new FindCartUserCommand());
        availableCommands.put(CommandName.DELETE_SONG_FROM_CART, new DeleteSongFromCartCommand());
        availableCommands.put(CommandName.PAY_ORDER, new PayOrderCommand());
        availableCommands.put(CommandName.FIND_SONG_USER, new FindSongUserCommand());
        availableCommands.put(CommandName.CREATE_COMMENT, new CreateCommentCommand());
        availableCommands.put(CommandName.ADD_FUNDS, new AddFundsCommand());
    }

    public static Command getCommand(String commandName) throws CommandException {
        if(commandName != null) {
            if (!commandName.isEmpty()) {
                try {
                    CommandName name = CommandName.valueOf(commandName.toUpperCase());
                    return availableCommands.get(name);
                }catch (IllegalArgumentException e) {
                    throw new CommandException("Invalid command", e);
                }
            } else throw new CommandException("Invalid command");
        }else return availableCommands.get(CommandName.GO_HOME);
    }
}
