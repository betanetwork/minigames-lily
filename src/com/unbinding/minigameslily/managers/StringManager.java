package com.unbinding.minigameslily.managers;

import com.unbinding.minigameslily.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class StringManager {

    public static void sendMessage(CommandSender sender, String message){
        sendRawMessage(sender, message);
    }

    public static String colorize(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendRawMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(message));
    }

    public static void sendServerMessage(CommandSender sender){
        String formattedMessage = colorize(purifyMessage(MM.NO_ARGS_SERVER, Main.getConnect().getSettings().getUsername(), null, null, null, null));
        sender.sendMessage(formattedMessage);
    }

    public static void sendStaffchatMessage(CommandSender sender, String message){
        String formattedMessage = colorize(purifyMessage(MM.STAFF_CHAT_FORMAT, Main.getConnect().getSettings().getUsername(), sender.getName(), null, null, message));
        Main.getRequestManager().asyncRequest(Collections.<String> emptyList(), "adminchat", formattedMessage);
    }

    public static String purifyMessage(String rawMessage, String serverName, String playerName, String command, Integer count, String subMessage) {
        if (serverName == null) {
            serverName = "";
        }
        if (playerName == null) {
            playerName = "";
        }
        if (command == null) {
            command = "";
        }
        if (count == null) {
            count = 0;
        }
        if (subMessage == null) {
            subMessage = "";
        }
        return rawMessage
                .replace("{server}", serverName)
                .replace("{player}", playerName)
                .replace("{command}", command)
                .replace("{count}", String.valueOf(count))
                .replace("{message}", subMessage);

    }


}
