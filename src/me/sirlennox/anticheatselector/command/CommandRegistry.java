package me.sirlennox.anticheatselector.command;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.Registry;
import me.sirlennox.anticheatselector.command.commands.*;

import java.util.Arrays;
import java.util.List;

public class CommandRegistry extends Registry<Command> {

    @Override
    public void init() {
        this.register(new CrashCommand());
        this.register(new DemoScreenCommand());
        this.register(new AntiCheatCommand());
        this.register(new SetHealthCommand());
        this.register(new SetHungerCommand());
        this.register(new ConfigCommand());
        this.register(new CrashKickCommand());
    }

    @Override
    public boolean register(Command... o) {

        Arrays.asList(o).forEach(c -> {
            AntiCheatSelector.INSTANCE.getCommand(c.command).setExecutor(c);
        });
        return super.register(o);
    }
}
