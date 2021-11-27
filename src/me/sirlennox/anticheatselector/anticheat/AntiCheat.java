package me.sirlennox.anticheatselector.anticheat;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;

public class AntiCheat {

    public String name;
    public String displayName;
    public ItemStack item;
    public List<String> bypassPermissions;
    public String permissionNeeded;

    public AntiCheat(String name, String displayName, ItemStack item, List<String> bypassPermissions, String permissionNeeded) {
        this.name = name;
        this.displayName = (displayName == null ? name : displayName);
        this.item = item;
        this.bypassPermissions = bypassPermissions;
        this.permissionNeeded = permissionNeeded;
    }

    public AntiCheat(String name, String displayName, ItemStack item, List<String> bypassPermissions) {
        this(name, displayName, item, bypassPermissions, null);
        this.permissionNeeded = getDefaultPermissions();
    }

    public String getDefaultPermissions() {
        return AntiCheatSelector.INSTANCE.permissionPrefix + ".anticheat." + name;
    }

    public void select(Player p) throws AntiCheatPermissionError {
        checkPermission(p, this.permissionNeeded);
        PermissionAttachment attachment = p.addAttachment(AntiCheatSelector.INSTANCE);
        for(String s : bypassPermissions) attachment.setPermission(s, false);
    }

    public void deselect(Player p) throws AntiCheatPermissionError {
        checkPermission(p, this.permissionNeeded);
        PermissionAttachment attachment = p.addAttachment(AntiCheatSelector.INSTANCE);
        for(String s : bypassPermissions) attachment.setPermission(s, true);
    }


    public void checkPermission(Player sender, String perm) throws AntiCheatPermissionError {
        if(perm == null) return;
        if(!sender.hasPermission(perm)) throw new AntiCheatPermissionError(perm, this);
    }

    public boolean toggle(Player p) throws AntiCheatPermissionError {
        if(isSelected(p)) {
            deselect(p);
            return false;
        }
        select(p);
        return true;
    }


    public boolean isSelected(Player p) {
        for(String s : bypassPermissions) if(p.hasPermission(s)) return false;
        return true;
    }



}
