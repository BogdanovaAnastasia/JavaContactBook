package org.itstep.msk;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Class to execute commands in loop
 * Non designed to be stopped from another thread or interrupted
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public final class CommandApplication {
    private final Map<String,Command> commands;
    private final Command onCommandNotFound;
    private final Supplier<String> controller;
    private boolean proceed;

    public CommandApplication(Map<String, Command> commands, Command onCommandNotFound, Supplier<String> controller) {
        this.commands = commands;
        this.onCommandNotFound = onCommandNotFound;
        this.controller = controller;
    }

    /**
     * Starts the main loop: read string -> find command -> execute command
     * If command not present in map, executes onCommandNotFound given in constructor
     *
     * @throws Exception might be throws by command
     * */
    public void start() throws Exception {
        proceed = true;
        while(proceed)
            commands.getOrDefault(controller.get(),onCommandNotFound).exec();
    }

    /**
     * Should be called to stop the main loop
     * For example, called from a command
     * */
    public void stop() {
        proceed = false;
    }
}
