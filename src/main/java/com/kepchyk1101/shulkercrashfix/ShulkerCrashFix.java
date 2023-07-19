package com.kepchyk1101.shulkercrashfix;

import com.kepchyk1101.shulkercrashfix.events.BlockDispenseListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ShulkerCrashFix extends JavaPlugin {

    private static ShulkerCrashFix instance;
    private final Logger log = getLogger();
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();

        instance = this;
        saveDefaultConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(new BlockDispenseListener(), this);

        double enabledTime = (double) (System.currentTimeMillis() - startTime) / 1000;
        log.info("Plugin has been enabled! (in " + enabledTime + " sec.)");
    }

    @Override
    public void onDisable() {
        log.info("ShulkerCrashFix has been disabled!");
    }

    public static ShulkerCrashFix getInstance() {
        return instance;
    }

    public Logger getLog() {
        return log;
    }

}