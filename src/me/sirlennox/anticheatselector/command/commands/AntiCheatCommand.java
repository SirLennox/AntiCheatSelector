package me.sirlennox.anticheatselector.command.commands;

import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.utils.AntiCheatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiCheatCommand extends Command {
    public AntiCheatCommand() {
        super("anticheat");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandError {
        if(!(commandSender instanceof Player)) throw new CommandError("This command is player-only", this);
         ((Player) commandSender).openInventory(AntiCheatUtils.createSelectAntiCheatInventory((Player) commandSender));
         return false;

    }
}
