package me.sirlennox.anticheatselector.config;

import me.sirlennox.anticheatselector.Registry;
import me.sirlennox.anticheatselector.config.configs.Kick;

import java.util.ArrayList;

public class ConfigRegistry extends Registry<Config> {

    public void init() {
        this.register(new Kick());
    }

    public Config getByName(String name) {
        return this.getByPredicate((c) -> c.name.equalsIgnoreCase(name));
    }

    public Config getByClass(Class<? extends Config> c) {
        return this.getByPredicate((c2) -> c2.getClass() == c);
    }

}
