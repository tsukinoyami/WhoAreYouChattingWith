package me.facu.util;

import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import net.skinsrestorer.api.property.SkinIdentifier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

public class SkinsManager {

    private final boolean skinsRestorerEnabled;
    public SkinsManager() {
        this.skinsRestorerEnabled = Bukkit.getPluginManager().isPluginEnabled("SkinsRestorer");
    }

    public String getHeadTarget(Player player) {
        if (skinsRestorerEnabled) {
            try {
                SkinsRestorer skinsRestorerAPI = SkinsRestorerProvider.get();
                Optional<SkinIdentifier> skinIdentifier = skinsRestorerAPI.getPlayerStorage().getSkinIdOfPlayer(player.getUniqueId());

                if (skinIdentifier.isPresent()) {
                    return skinIdentifier.get().getIdentifier();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return player.getName();
    }
}
