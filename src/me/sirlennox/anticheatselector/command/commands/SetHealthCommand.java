package me.sirlennox.anticheatselector.command.commands;

import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.command.exceptions.CommandUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealthCommand extends Command {
    public SetHealthCommand() {
        super("sethealth");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandUsage, CommandError {
        if(!(commandSender instanceof Player)) throw new CommandError("This command is player-only", this);
        if(args.length < 1) throw new CommandUsage("<Health>", this);
        try {
            int h = Integer.parseInt(args[0]);
            if (!(h > 0 && h <= 2048)) throw new CommandError("Health must be between 1 and 2048", this);
            ((Player) commandSender).setMaxHealth(h);
            ((Player) commandSender).setHealth(h);
            commandSender.sendMessage("§aSuccessfully set your health to §e" + h);
        }catch (NumberFormatException e) {
            throw new CommandError("Not a valid number!", this);
        }
        return false;
    }
}
