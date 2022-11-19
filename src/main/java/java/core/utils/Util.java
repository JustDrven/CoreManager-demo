package java.core.utils;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static List<String> authServers = Arrays.asList("Auth-1", "Auth-2", "Auth-3");
    private static String message = null;

    public static String showAllAuthServer() {
        authServers.stream().filter(c -> c.contains("Auth")).forEach(serversss -> {
            message = message + ";" + serversss;
        });
        return message;
    }

}
