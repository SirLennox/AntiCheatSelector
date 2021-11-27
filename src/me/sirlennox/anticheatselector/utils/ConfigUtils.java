package me.sirlennox.anticheatselector.utils;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.config.Config;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ConfigUtils {

    public static Inventory createSelectConfigInventory(Player opener) {
        int invSize = AntiCheatSelector.INSTANCE.configRegistry.getRegistered().size();
        while (invSize % 9 != 0) invSize++;
        return addConfigsToInventory(Bukkit.createInventory(null, invSize, "§e§lConfigs"), opener);
    }

    public static Inventory addConfigsToInventory(Inventory inv, Player opener) {
        for(Config cfg : AntiCheatSelector.INSTANCE.configRegistry.getRegistered()) {
            ItemStack is = cfg.item.clone();
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(cfg.displayName);
            im.setLore(Collections.singletonList((cfg.isSelected(opener) ? "§aActivated" : "§cDeactivated")));
            is.setItemMeta(im);

            NBTTagCompound tag = NBTUtils.getItemTag(is);
            tag.setString("config", cfg.name);
            is = NBTUtils.setItemTag(is, tag);

            inv.addItem(is);
        }
        return inv;
    }

}
