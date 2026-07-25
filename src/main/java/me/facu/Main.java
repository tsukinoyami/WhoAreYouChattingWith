package me.facu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatAsync(), this);
        if (Bukkit.getPluginManager().isPluginEnabled("SkinsRestorer")){
            getLogger().info("WACYW Enabled and connected to SkinsRestorer!");
        } else {
            getLogger().info("WAYCW Enabled!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("WAYCW Disabled!");
    }
}