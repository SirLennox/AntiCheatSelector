package me.sirlennox.anticheatselector.utils;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.anticheat.AntiCheat;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class AntiCheatUtils {

    public static Inventory createSelectAntiCheatInventory(Player opener) {
        int invSize = AntiCheatSelector.INSTANCE.antiCheatRegistry.getRegistered().size();
        while (invSize % 9 != 0) invSize++;
        return addAntiCheatsToInventory(Bukkit.createInventory(null, invSize, "§c§lAntiCheats§r"), opener);
    }

    public static Inventory addAntiCheatsToInventory(Inventory inv, Player opener) {
        for(AntiCheat ac : AntiCheatSelector.INSTANCE.antiCheatRegistry.getRegistered()) {
            ItemStack is = ac.item.clone();
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ac.displayName);
            im.setLore(Collections.singletonList((ac.isSelected(opener) ? "§aSelected" : "§cNot selected")));
            is.setItemMeta(im);

            NBTTagCompound tag = NBTUtils.getItemTag(is);
            tag.setString("anticheat", ac.name);
            is = NBTUtils.setItemTag(is, tag);

            inv.addItem(is);
        }
        return inv;
    }

}
