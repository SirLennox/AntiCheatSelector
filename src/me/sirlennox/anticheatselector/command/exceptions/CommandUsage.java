package me.sirlennox.anticheatselector.command.exceptions;

import me.sirlennox.anticheatselector.command.Command;

public class CommandUsage extends CommandError {

    public CommandUsage(String usage, Command cmd) {
        super(usage, cmd);
    }

    @Override
    public String getMessage() {
        return "§4Usage: §c/" + cmd.command + " " + error;
    }
}
