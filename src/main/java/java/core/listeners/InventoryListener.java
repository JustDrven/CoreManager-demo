package java.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().isEmpty() || e.getInventory().getName().isEmpty() || e.getInventory()
                .getTitle().equals("") || e.getInventory().getName().equals("")) {
            return;
        }
        if (e.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }
    }

}
