package rip.snicon.connection;

import net.minestom.server.entity.Player;

public class Connection {
    // TODO Add JavaDoc and then functionality
    public static void connectToServer(Player player, String serverName){
        player.sendMessage("Connecting to server " + serverName + "...");
    }
}
