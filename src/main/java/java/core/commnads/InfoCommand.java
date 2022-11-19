package java.core.commnads;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.core.utils.Colors;
import java.core.utils.CoreManager;

public class InfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(CoreManager.getPrefix() + "&7Your Name: &a"+ p.getName());
                p.sendMessage(CoreManager.getPrefix() + "&7Your UUID: &a"+ p.getUniqueId().toString());
                p.sendMessage(CoreManager.getPrefix() + "&7Server: &a"+ Bukkit.getServer().getServerName());
            } else {
                p.sendMessage(CoreManager.getPrefix() + "&7Usage: &a/info");
                return false;
            }
        } else {
            sender.sendMessage(Colors.format("&cYou're not player but console!"));
            return false;
        }
        return true;
    }
}
