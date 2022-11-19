package java.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.core.utils.Colors;
import java.core.utils.CoreManager;

public class ServerListener implements Listener {

    @EventHandler
    public void on(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
        e.setDeathMessage(null);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL)) {
            if (e.getPlayer().hasPermission("coremanager.listeners.join.vip")) {
                e.allow();
            } else {
                e.setKickMessage(Colors.format(CoreManager.getPrefix() + "&cThis server is offline!"));
            }
        }
    }

}
