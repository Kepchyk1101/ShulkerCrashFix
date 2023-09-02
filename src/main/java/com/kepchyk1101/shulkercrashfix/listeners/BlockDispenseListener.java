package com.kepchyk1101.shulkercrashfix.listeners;

import com.kepchyk1101.shulkercrashfix.ShulkerCrashFix;
import com.kepchyk1101.shulkercrashfix.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import java.util.logging.Logger;

public class BlockDispenseListener implements Listener {

    private final Logger log = ShulkerCrashFix.getInstance().getLogger();
    private final FileConfiguration config = ShulkerCrashFix.getInstance().getConfig();

    private final boolean isConsoleNotifyEnabled = config.getBoolean("console-notify.enable");
    private final String consoleNotifyMessage = config.getString("console-notify.message");

    private final boolean isIngameNotifyEnabled = config.getBoolean("ingame-notify.enable");
    private final String ingamePermission = config.getString("ingame-notify.permission");
    private final String ingameNotifyMessage = config.getString("ingame-notify.message");

    @EventHandler
    private void onBlockDispense(BlockDispenseEvent event) {

        Block block = event.getBlock();
        short blockY = (short) block.getY();

        if (event.getItem().getType().name().endsWith("SHULKER_BOX") && (blockY > 254 || blockY < 1)) {

            event.setCancelled(true);

            if (isConsoleNotifyEnabled)
                log.warning(replacePlaceholders(consoleNotifyMessage, block));

            if (isIngameNotifyEnabled)
                for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                    if (onlinePlayer.hasPermission(ingamePermission))
                        ChatUtils.sendMessage(onlinePlayer, replacePlaceholders(ingameNotifyMessage, block));

        }

    }

    private String replacePlaceholders(String str, Block block) {
        return str
                .replace("{X}", String.valueOf(block.getX()))
                .replace("{Y}", String.valueOf(block.getY()))
                .replace("{Z}", String.valueOf(block.getZ()));
    }

}