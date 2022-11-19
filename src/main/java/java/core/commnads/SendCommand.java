package java.core.commnads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.core.Main;
import java.core.utils.Colors;
import java.core.utils.CoreManager;

public class SendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("coremanager.commands.send.*")) {
                if (args.length == 0) {
                    p.sendMessage(CoreManager.getPrefix() + "&7Usage: &a/c <server>");
                    return false;
                } else {
                    Main.send(p, args[0]);
                    return false;
                }
            } else {
                p.sendMessage(Colors.format(Main.getInstance().getConfig().getString("permission-message")));
                return false;
            }
        }
        return true;
    }
}
