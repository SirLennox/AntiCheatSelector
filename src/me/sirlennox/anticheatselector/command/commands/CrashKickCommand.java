package me.sirlennox.anticheatselector.command.commands;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.command.exceptions.CommandUsage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrashKickCommand extends Command {
    public CrashKickCommand() {
        super("crashkick");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandUsage, CommandError {
        if (args.length < 1) throw new CommandUsage("<Player>", this);
        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) throw new CommandError("Player not found!", this);
        p.kickPlayer(AntiCheatSelector.INSTANCE.crashString);
        commandSender.sendMessage("§aSuccessfully Crash-Kicked Player!");
        return false;
    }
}
