package me.sirlennox.anticheatselector.command.exceptions;

import me.sirlennox.anticheatselector.command.Command;

public class CommandPermissionError extends CommandError {


    public CommandPermissionError(String perm, Command cmd) {
        super(perm, cmd);
    }

    @Override
    public String getMessage() {
        return "§4You do not have enough permissions to execute this command §c(§e" + error + "§c)";
    }
}
