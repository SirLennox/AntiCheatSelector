package me.sirlennox.anticheatselector.anticheat;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.Registry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AntiCheatRegistry extends Registry<AntiCheat> {

    public void init() {
        if (!AntiCheatSelector.INSTANCE.getConfig().isSet("anticheats")) {
            Bukkit.getLogger().info("You have to set the anticheats object in the config.yml");
            return;
        }
        try {
            MemorySection anticheatsSection = (MemorySection) AntiCheatSelector.INSTANCE.getConfig().get("anticheats");
            Set<String> anticheats = anticheatsSection.getKeys(false);
            for (String s : anticheats) {
                MemorySection acSection = (MemorySection) anticheatsSection.get(s);
                String name = acSection.getString("name");
                String displayName = acSection.isSet("displayName") ? acSection.getString("displayName") : null;
                String itemName = acSection.getString("item");
                Short itemDamage = (acSection.contains("itemDamage") ? ((Number) acSection.getInt("itemDamage")).shortValue() : null);
                List<String> acPerms = acSection.getStringList("bypassPermissions");
                String perm = (acSection.isSet("permission") ? (String) acSection.get("permission") : null);
                AntiCheat ac;
                ItemStack is = new ItemStack(Material.valueOf(itemName.toUpperCase()));
                if (itemDamage != null) is.setDurability(itemDamage);
                if (perm != null && perm.equalsIgnoreCase("none")) {
                    ac = new AntiCheat(name, displayName, is, acPerms);
                } else {
                    ac = new AntiCheat(name, displayName, is, acPerms, perm);
                }
                this.register(ac);
            }
        } catch (Throwable t) {
            t.printStackTrace();
            Bukkit.getLogger().warning("Something went wrong.");
        }
    }

    public AntiCheat getByName(String name) {
        return this.getByPredicate(a -> a.name.equalsIgnoreCase(name));
    }


}
