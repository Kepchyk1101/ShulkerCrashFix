package com.kepchyk1101.shulkercrashfix;

import com.kepchyk1101.shulkercrashfix.listeners.BlockDispenseListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ShulkerCrashFix extends JavaPlugin {

    private static ShulkerCrashFix instance;
    private final Logger log = getLogger();

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BlockDispenseListener(), this);

        new Metrics(this, 19841);

        log.info("ShulkerCrashFix has been enabled!");

    }

    @Override
    public void onDisable() {
        log.info("ShulkerCrashFix has been disabled!");
    }

    public static ShulkerCrashFix getInstance() {
        return instance;
    }

}