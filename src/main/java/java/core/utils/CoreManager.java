package java.core.utils;

import java.core.Main;

public class CoreManager {

    public static String getPrefix() {
        return Colors.format(Main.getInstance().getConfig().getString("prefix"));
    }

    public static String getConnect() {
        return Colors.format(getPrefix() + "&7Connecting to &a{SERVER}...");
    }

}
