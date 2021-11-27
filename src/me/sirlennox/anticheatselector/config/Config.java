package me.sirlennox.anticheatselector.config;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

public class Config {

    public String name;
    public String displayName;
    public String valuePermissions;
    public ItemStack item;


    public Config(String name, String displayName, ItemStack item) {
        this.name = name;
        this.displayName = (displayName == null ? name : displayName);
        this.item = item;
        this.valuePermissions = getDefaultPermissions();
    }

    public String getDefaultPermissions() {
        return AntiCheatSelector.INSTANCE.permissionPrefix + ".config." + name;
    }
    public void select(Player p) {
        PermissionAttachment attachment = p.addAttachment(AntiCheatSelector.INSTANCE);
        attachment.setPermission(valuePermissions, true);
    }

    public void deselect(Player p) {
        PermissionAttachment attachment = p.addAttachment(AntiCheatSelector.INSTANCE);
        attachment.setPermission(valuePermissions, false);
    }


    public boolean toggle(Player p) {
        if(isSelected(p)) {
            deselect(p);
            return false;
        }
        select(p);
        return true;
    }


    public boolean isSelected(Player p) {
        return p.hasPermission(valuePermissions);
    }

}
