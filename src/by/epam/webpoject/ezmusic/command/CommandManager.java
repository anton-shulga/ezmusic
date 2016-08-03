package by.epam.webpoject.ezmusic.command;

import by.epam.webpoject.ezmusic.command.impl.*;
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
    }

    public static Command getCommand(String commandName) throws CommandException {
        if(commandName != null) {
            if (!commandName.isEmpty()) {
                CommandName name = CommandName.valueOf(commandName.toUpperCase());
                return availableCommands.get(name);
            } else throw new CommandException("Invalid command");
        }else return availableCommands.get(CommandName.GO_HOME);
    }
}
