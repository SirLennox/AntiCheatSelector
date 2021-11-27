package me.sirlennox.anticheatselector.utils;

import me.sirlennox.anticheatselector.AntiCheatSelector;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

public class NBTUtils {

    public static net.minecraft.server.v1_8_R3.Entity getNSMEntity(Entity e) {
        return ((CraftEntity) e).getHandle();
    }

    public static NBTTagCompound getItemTag(org.bukkit.inventory.ItemStack is) {
        ItemStack nsm = getNSMItemStack(is);
        if(nsm == null) return new NBTTagCompound();
        return nsm.getTag() == null ? new NBTTagCompound() : nsm.getTag();
    }


    public static org.bukkit.inventory.ItemStack setItemTag(org.bukkit.inventory.ItemStack is, NBTTagCompound tag) {
        ItemStack nsm = getNSMItemStack(is);
        if(nsm == null) return is;
        nsm.setTag(tag);
        return getCraftItemStack(nsm);
    }

    public static Entity getCraftEntity(net.minecraft.server.v1_8_R3.Entity e) {
        return CraftEntity.getEntity((CraftServer) Bukkit.getServer(), e);
    }

    public static Object getEntityTag(Entity e, String tag) {
        return e.getMetadata(tag).stream().findFirst().orElse(null);
    }

    public static void setEntityTag(Entity e, String tag, Object value) {
        e.setMetadata(tag, new FixedMetadataValue(AntiCheatSelector.INSTANCE, value));
    }

    public static ItemStack getNSMItemStack(org.bukkit.inventory.ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }


    public static org.bukkit.inventory.ItemStack getCraftItemStack(ItemStack itemStack) {
        return CraftItemStack.asCraftMirror(itemStack);
    }




}
