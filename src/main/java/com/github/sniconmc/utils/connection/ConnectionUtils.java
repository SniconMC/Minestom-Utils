package com.github.sniconmc.utils.connection;

import net.minestom.server.entity.Player;

public class ConnectionUtils {
    // TODO Add JavaDoc and then functionality
    public static void connectToServer(Player player, String serverName){
        player.sendMessage("Connecting to server " + serverName + "...");
    }
}
