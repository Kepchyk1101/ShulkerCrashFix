package com.kepchyk1101.shulkercrashfix.events;

import com.kepchyk1101.shulkercrashfix.ShulkerCrashFix;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import java.util.logging.Logger;

public class BlockDispenseListener implements Listener {

    private final Logger log = ShulkerCrashFix.getInstance().getLog();

    private final boolean console_notify = ShulkerCrashFix.config.getBoolean("console-notify.enable");
    private final String console_message = ShulkerCrashFix.config.getString("console-notify.message");

    private final boolean ingame_notify = ShulkerCrashFix.config.getBoolean("ingame-notify.enable");
    private final String ingame_permission = ShulkerCrashFix.config.getString("ingame-notify.permission");
    private final String ingame_message = ShulkerCrashFix.config.getString("ingame-notify.message");

    @EventHandler
    public void onBlockDispenese(BlockDispenseEvent event) {

        Block block = event.getBlock();
        short block_Y = (short) block.getY();
        String block_Name = event.getItem().getType().name();

        if (block_Name.endsWith("SHULKER_BOX") && block_Y > 254 | block_Y < 1) {
            event.setCancelled(true);

            short block_X = (short) block.getX();
            short block_Z = (short) block.getZ();

            if (console_notify) {
                log.warning(console_message
                        .replace("&", "§")
                        .replace("{X}", String.valueOf(block_X))
                        .replace("{Y}", String.valueOf(block_Y))
                        .replace("{Z}", String.valueOf(block_Z)));
            }

            if (ingame_notify) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.hasPermission(ingame_permission))
                        onlinePlayer.sendMessage(ingame_message
                                .replace("&", "§")
                                .replace("{X}", String.valueOf(block_X))
                                .replace("{Y}", String.valueOf(block_Y))
                                .replace("{Z}", String.valueOf(block_Z)));
                }
            }

        }

    }

}