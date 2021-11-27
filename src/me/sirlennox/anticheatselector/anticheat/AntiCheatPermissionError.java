package me.sirlennox.anticheatselector.anticheat;

import me.sirlennox.anticheatselector.command.exceptions.CommandPermissionError;

public class AntiCheatPermissionError extends CommandPermissionError {
    public String perm;
    public AntiCheat ac;
    public AntiCheatPermissionError(String perm, AntiCheat ac) {
        super(perm, null);
        this.perm = perm;
        this.ac = ac;
    }

    @Override
    public String getMessage() {
        return "§4You do not have enough permissions to use this anticheat §c[§e" + ac.name + "§c] §c(§e" + perm + "§c)";
    }
}
