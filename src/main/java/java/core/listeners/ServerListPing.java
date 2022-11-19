package java.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.core.Main;
import java.core.utils.Colors;

public class ServerListPing implements Listener {

    @EventHandler
    public void on(ServerListPingEvent e) {
        e.setMotd(Colors.format(Main.getInstance().getConfig().getString("motd")));
    }

}
