package me.sirlennox.anticheatselector.command.commands;

import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.command.exceptions.CommandUsage;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class DemoScreenCommand extends Command {
    public DemoScreenCommand() {
        super("demoscreen");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandUsage, CommandError {
        if(args.length < 1) throw new CommandUsage("<Player>", this);
        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) throw new CommandError("Player not found!", this);
        CraftPlayer cp = (CraftPlayer) p;
        cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0));
        commandSender.sendMessage("§aSuccessfully sent demoscreen player!");
        return false;
    }
}
