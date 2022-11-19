package java.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.core.Main;
import java.core.utils.CoreManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Main.players.add(p);
        if (Main.players.contains(p)) {
            p.sendMessage(CoreManager.getConnect().replace("{SERVER}", Main.getInstance().getConfig().getString("server-name")));
        } else {
            Main.players.add(p);
        }
    }

    @EventHandler
    public void on(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Main.players.remove(p);
    }

}
