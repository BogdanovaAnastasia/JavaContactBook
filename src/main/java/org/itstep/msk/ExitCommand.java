package org.itstep.msk;

/**
 * Special type of command to stop the CommandApplication
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public class ExitCommand implements Command {
    private final CommandApplication toStop;

    public ExitCommand(CommandApplication toStop) {
        this.toStop = toStop;
    }

    /**
     * Calls a stop method of the CommandApplication supplied with constructor
     * */
    @Override
    public void exec() {
        toStop.stop();
    }
}
