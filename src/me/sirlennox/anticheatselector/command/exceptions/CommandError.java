package me.sirlennox.anticheatselector.command.exceptions;

import me.sirlennox.anticheatselector.command.Command;

public class CommandError extends Exception {

    public String error;
    public Command cmd;

    public CommandError(String error, Command cmd) {
        this.error = error;
        this.cmd = cmd;
    }

    @Override
    public String getMessage() {
        return "§4Error: §c" + error;
    }
}
