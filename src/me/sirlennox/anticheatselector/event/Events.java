package me.sirlennox.anticheatselector.event;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import me.sirlennox.anticheatselector.anticheat.AntiCheat;
import me.sirlennox.anticheatselector.anticheat.AntiCheatPermissionError;
import me.sirlennox.anticheatselector.config.Config;
import me.sirlennox.anticheatselector.config.configs.Kick;
import me.sirlennox.anticheatselector.utils.AntiCheatUtils;
import me.sirlennox.anticheatselector.utils.ConfigUtils;
import me.sirlennox.anticheatselector.utils.NBTUtils;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
    @EventHandler
    public static void onInvClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        ItemStack is = e.getCurrentItem();
        if(is == null) return;
        NBTTagCompound tag = NBTUtils.getItemTag(is);
        if(tag == null) return;


        if(tag.hasKey("anticheat")) {
            e.setCancelled(true);
            AntiCheat ac = AntiCheatSelector.INSTANCE.antiCheatRegistry.getByName(tag.getString("anticheat"));
            if (ac == null) return;

            try {
                if (ac.toggle((Player) e.getWhoClicked())) ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.SUCCESSFUL_HIT, 10, 10);else ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ANVIL_BREAK, 10, 5);
            } catch (AntiCheatPermissionError antiCheatPermissionError) {
                e.getWhoClicked().sendMessage(antiCheatPermissionError.getMessage());
            }
            e.getInventory().clear();
            AntiCheatUtils.addAntiCheatsToInventory(e.getInventory(), (Player) e.getWhoClicked());
        }else if(tag.hasKey("config")) {
            e.setCancelled(true);
            Config cfg = AntiCheatSelector.INSTANCE.configRegistry.getByName(tag.getString("config"));
            if(cfg == null) return;
            if (cfg.toggle((Player) e.getWhoClicked())) ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.SUCCESSFUL_HIT, 10, 10);else ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ANVIL_BREAK, 10, 5);
            e.getInventory().clear();
            ConfigUtils.addConfigsToInventory(e.getInventory(), (Player) e.getWhoClicked());
        }
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("§d" + e.getPlayer().getDisplayName() + " §r§7joined.");
    }


    @EventHandler
    public static void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage("§d" + e.getPlayer().getDisplayName() + " §r§7left.");
    }

    @EventHandler
    public static void onKick(PlayerKickEvent e) {
        e.setCancelled(!AntiCheatSelector.INSTANCE.configRegistry.getByClass(Kick.class).isSelected(e.getPlayer()));
        if(e.isCancelled()) {
            ((CraftPlayer) e.getPlayer()).sendTitle("§cYou would be kicked now!", "§cReason: §f" + e.getReason());
        }
    }


    /**
     * Worldprotect (Break)
     * @param e Event
     */

    @EventHandler
    public static void onBreak(BlockBreakEvent e) {
        e.setCancelled(!e.getPlayer().hasPermission("anticheatspace.block.break"));
    }


    /**
     * Worldprotect (Place)
     * @param e Event
     */
    @EventHandler
    public static void onPlace(BlockPlaceEvent e) {
        e.setCancelled(!e.getPlayer().hasPermission("anticheatspace.block.place"));
    }

    /**
     * Adds respawn tag to entities
     * @param e Event
     */
    @EventHandler
    public static void onDamage(EntityDamageEvent e) {
        Object tag = NBTUtils.getEntityTag(e.getEntity(), "nodamage");
        if(tag != null && (boolean) tag) e.setDamage(0);

    }

}
