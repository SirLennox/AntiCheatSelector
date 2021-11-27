package me.sirlennox.anticheatselector;

import me.sirlennox.anticheatselector.anticheat.AntiCheatRegistry;
import me.sirlennox.anticheatselector.command.CommandRegistry;
import me.sirlennox.anticheatselector.config.ConfigRegistry;
import me.sirlennox.anticheatselector.event.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCheatSelector extends JavaPlugin {

    public static AntiCheatSelector INSTANCE;
    public CommandRegistry commandRegistry;
    public AntiCheatRegistry antiCheatRegistry;
    public ConfigRegistry configRegistry;
    public String permissionPrefix;
    public String crashString;

    @Override
    public void onEnable() {
        INSTANCE = this;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++)
            sb.append("§k§l\n");
        crashString = sb.toString();
        this.saveDefaultConfig();
        this.permissionPrefix = this.getName().toLowerCase();
        this.commandRegistry = new CommandRegistry();
        this.commandRegistry.init();
        this.antiCheatRegistry = new AntiCheatRegistry();
        this.antiCheatRegistry.init();
        this.configRegistry = new ConfigRegistry();
        this.configRegistry.init();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        super.onEnable();
    }
}
