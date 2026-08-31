package me.facu;

import me.facu.listener.ChatListener;
import me.facu.listener.JoinLeaveListener;
import me.facu.util.SkinsManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class WhoAreYouChattingWith extends JavaPlugin {

    private static WhoAreYouChattingWith thisPlugin;

    @Override
    public void onEnable() {
        thisPlugin = this;
        saveDefaultConfig();
        SkinsManager skinsManager = new SkinsManager();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChatListener(skinsManager), this);
        pm.registerEvents(new JoinLeaveListener(skinsManager), this);

        enviarBannerConsola();
    }

    @Override
    public void onDisable() {
        getLogger().info("WAYCW Disabled!");
    }


    private void enviarBannerConsola() {
        MiniMessage mm = MiniMessage.miniMessage();
        boolean skinsRestorer = Bukkit.getPluginManager().isPluginEnabled("SkinsRestorer");

        String[] banner = {
                "<gradient:#38ef7d:#11998e>  _       _____  __  ______ _       __</gradient>",
                "<gradient:#38ef7d:#11998e> | |     / /   | \\ \\/ / ____| |     / /</gradient>  <white><bold>WhoAreYouChattingWith</bold></white>",
                "<gradient:#38ef7d:#11998e> | | /| / / /| |  \\  / /    | | /| / / </gradient>  <gray>By <green>tsukinoyami</green></gray>",
                "<gradient:#38ef7d:#11998e> | |/ |/ / ___ |  / / /___  | |/ |/ /  </gradient>  <gray>SkinsRestorer: " + (skinsRestorer ? "<green>Detected</green>" : "<red>Not Detected</red>") + "</gray>",
                "<gradient:#38ef7d:#11998e> |__/|__/_/  |_| /_/\\_____/ |__/|__/   </gradient>  <dark_gray>Plugin loaded successfully.</dark_gray>",
                ""
        };

        for (String linea : banner) {
            Bukkit.getConsoleSender().sendMessage(mm.deserialize(linea));
        }
    }

    public static WhoAreYouChattingWith returnSelf(){
        return thisPlugin;
    }
}

