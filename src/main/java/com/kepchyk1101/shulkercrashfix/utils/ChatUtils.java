package com.kepchyk1101.shulkercrashfix.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(format(message));
    }

    private static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}