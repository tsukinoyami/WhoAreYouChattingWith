package me.facu;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.skinsrestorer.api.PropertyUtils;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import net.skinsrestorer.api.property.SkinIdentifier;
import net.skinsrestorer.api.property.SkinProperty;
import net.skinsrestorer.api.storage.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

public class ChatAsync implements Listener {
    private final MiniMessage mm = MiniMessage.miniMessage();

    public ChatAsync() {
    }

    @EventHandler
    public void onMessage(AsyncChatEvent e) {
        SkinsRestorerProvider.get();
        Player player = e.getPlayer();
        String text = PlainTextComponentSerializer.plainText().serialize(e.message());

        String target = getHeadTarget(player);

        String chatForm = "<head:" + target + ":true> " +
                "<white><" + player.getName() + "> " +
                text;

        Component mensajeFinal = mm.deserialize(chatForm);

        e.renderer((source, sourceDisplayName, message, viewer) -> mensajeFinal);
    }

    public String getHeadTarget(Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("SkinsRestorer")) {
            try {
                SkinsRestorer skinsRestorerAPI = SkinsRestorerProvider.get();
                PlayerStorage playerStorage = skinsRestorerAPI.getPlayerStorage();

                Optional<SkinIdentifier> skinIdentifier = playerStorage.getSkinIdOfPlayer(player.getUniqueId());

                if (skinIdentifier.isPresent()) {
                    SkinIdentifier skinIdentifier1 = skinIdentifier.get();
                    return skinIdentifier1.getIdentifier();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return player.getName();
    }
}