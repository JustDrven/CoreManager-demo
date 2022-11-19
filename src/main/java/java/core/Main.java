package java.core;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.core.commnads.InfoCommand;
import java.core.commnads.SendCommand;
import java.core.listeners.ServerListPing;
import java.core.utils.Colors;
import java.core.utils.CoreManager;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ProtocolManager manager;

    public static Main getInstance() {
        return instance;
    }

    public static ProtocolManager getManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        // systems & instance
        instance = this;
        saveDefaultConfig();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        manager = ProtocolLibrary.getProtocolManager();
        // listeners
        getServer().getPluginManager().registerEvents(new ServerListPing(), this);
        // commands
        getCommand("info").setExecutor(new InfoCommand());
        getCommand("c").setExecutor(new SendCommand());
        // manager
        getManager().addPacketListener(new PacketAdapter(
                this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT
        ) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                String message = packet.getStrings().read(0);

                if (message.contains("shit") || message.contains("kokot") || message.contains("pica") || message.contains("www.") || message.contains(".com") || message.contains("ts3.") || message.contains("mc.")) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Colors.format(CoreManager.getPrefix() + "&cThis message is not allowed!"));
                }
            }
        });

        // logger
        System.out.println("[MCCore] - Plugin was been enabled!");
        if (Bukkit.getServer().getOnlineMode() == true) {
            System.out.println("[INFO] - OnlineMod is enabled!");
        }
        else if (Bukkit.getServer().getOnlineMode() == false) {
            System.out.println("[INFO] - OnlineMod is disabled!");
        } else {
            System.out.println("[INFO] - Error wit OnlineMod");
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        HandlerList.unregisterAll();
        System.out.println("[MCCore] - Plugin was been disabled!");
    }

    public static void send(Player p, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
            p.sendMessage(Colors.format(CoreManager.getConnect().replace("{SERVER}", server)));
        } catch (IOException localIOException) {
            p.sendMessage(CoreManager.getPrefix() + "&cError");
        }
        p.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }

    public static int killAllMobs(String world) {
        if (Bukkit.getWorld(world) != null) {
            for (LivingEntity e : Bukkit.getWorld(world).getLivingEntities()) {
                if (!(e instanceof Player)) {
                    LivingEntity entity = e;
                    if (entity instanceof ArmorStand) {
                        ArmorStand armor = (ArmorStand) entity;
                        armor.setGravity(false);
                    }
                    if ((entity.getType() != EntityType.PLAYER) && (entity.getType() != EntityType.ARMOR_STAND) && (entity.getType() != EntityType.ITEM_FRAME) && (entity.getType() != EntityType.MINECART) && (entity.getType() != EntityType.VILLAGER)) {
                        entity.remove();
                    }
                }
            }
        }
        return 0;
    }

}
