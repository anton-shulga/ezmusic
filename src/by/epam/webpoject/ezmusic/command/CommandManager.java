package by.epam.webpoject.ezmusic.command;

import by.epam.webpoject.ezmusic.command.impl.ChangeLocaleCommand;
import by.epam.webpoject.ezmusic.command.impl.GoHomeCommand;
import by.epam.webpoject.ezmusic.command.impl.LogoutCommand;
import by.epam.webpoject.ezmusic.command.impl.album.*;
import by.epam.webpoject.ezmusic.command.impl.author.*;
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
        availableCommands.put(CommandName.LOGIN, new LoginCommand());
        availableCommands.put(CommandName.REGISTER, new RegisterUserCommand());
        availableCommands.put(CommandName.DELETE_USER, new DeleteUserCommand());
        availableCommands.put(CommandName.FIND_USER, new FindUserCommand());
        availableCommands.put(CommandName.UPDATE_USER, new UpdateUserCommand());
        availableCommands.put(CommandName.CHECK_LOGIN_AVAILABILITY, new CheckLoginAvailabilityCommand());
        availableCommands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        availableCommands.put(CommandName.FIND_ALL_SONGS, new FindAllSongsCommand());
        availableCommands.put(CommandName.LOGOUT, new LogoutCommand());
        availableCommands.put(CommandName.FIND_SONG_FOR_UPDATE, new FindSongForUpdateCommand());
        availableCommands.put(CommandName.FIND_SONG_FOR_CREATE, new FindSongForCreateCommand());
        availableCommands.put(CommandName.CREATE_SONG, new CreateSongCommand());
        availableCommands.put(CommandName.UPDATE_SONG, new UpdateSongCommand());
        availableCommands.put(CommandName.DELETE_SONG, new DeleteSongCommand());
        availableCommands.put(CommandName.CREATE_ALBUM, new CreateAlbumCommand());
        availableCommands.put(CommandName.UPDATE_ALBUM, new UpdateAlbumCommand());
        availableCommands.put(CommandName.DELETE_ALBUM, new DeleteAlbumCommand());
        availableCommands.put(CommandName.FIND_ALL_ALBUMS, new FindAllAlbumsCommand());
        availableCommands.put(CommandName.FIND_ALBUM_FOR_CREATE, new FindAlbumForCreateCommand());
        availableCommands.put(CommandName.FIND_ALBUM_FOR_UPDATE, new FindAlbumForUpdateCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_ALBUMS_JSON, new FindAuthorAlbumsJsonCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_SONGS_JSON, new FindAuthorSongsJsonCommand());
        availableCommands.put(CommandName.FIND_ALL_AUTHORS, new FindAllAuthorsCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_FOR_CREATE, new FindAuthorForCreateCommand());
        availableCommands.put(CommandName.FIND_ALBUM_SONGS_JSON, new FindAlbumSongsJsonCommand());
        availableCommands.put(CommandName.CREATE_AUTHOR, new CreateAuthorCommand());
        availableCommands.put(CommandName.UPDATE_AUTHOR, new UpdateAuthorCommand());
        availableCommands.put(CommandName.FIND_AUTHOR_FOR_UPDATE, new FindAuthorForUpdateCommand());
        availableCommands.put(CommandName.DELETE_AUTHOR, new DeleteAuthorCommand());
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
