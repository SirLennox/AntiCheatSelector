package me.sirlennox.anticheatselector.command.commands;

import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.utils.ConfigUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandError {
        if(!(commandSender instanceof Player)) throw new CommandError("This command is player-only", this);
         ((Player) commandSender).openInventory(ConfigUtils.createSelectConfigInventory((Player) commandSender));
         return false;

    }
}
