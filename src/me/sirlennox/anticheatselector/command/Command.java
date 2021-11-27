package me.sirlennox.anticheatselector.command;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.command.exceptions.CommandPermissionError;
import me.sirlennox.anticheatselector.command.exceptions.CommandUsage;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class Command implements CommandExecutor {
    public String command;
    public String perm;

    public Command(String command, String perm) {
        this.command = command;
        this.perm = perm == null ? getDefaultPermission() : perm;
    }

    public Command(String command) {
        this(command, null);
    }

    public String getDefaultPermission() {
        return AntiCheatSelector.INSTANCE.permissionPrefix + ".command." + command;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        try {
            checkPermission(commandSender, perm);
            return execute(commandSender, command, label, args);
        } catch (CommandError commandError) {
            commandSender.sendMessage(commandError.getMessage());
        }
        return true;
    }

    public abstract boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandUsage, CommandError;

    public void checkPermission(CommandSender sender, String perm) throws CommandPermissionError {
        if(perm == null) return;
        if(!sender.hasPermission(perm)) throw new CommandPermissionError(perm, this);
    }

}
