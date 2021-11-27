package me.sirlennox.anticheatselector.config.configs;

import me.sirlennox.anticheatselector.config.Config;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Kick extends Config {
    public Kick() {
        super("kick", "§cKick", new ItemStack(Material.BARRIER, 1));
    }
}
