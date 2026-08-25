package me.facu.listener;

/*
    añadir /msg
    configurar el config.yml
    añadir customizacion del render del chat


*/
import io.papermc.paper.event.player.AsyncChatEvent;
import me.facu.util.SkinsManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {
    private final SkinsManager skinsManager;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public ChatListener(SkinsManager skinsManager) {
        this.skinsManager = skinsManager;
    }

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        Player player = e.getPlayer();
        String text = PlainTextComponentSerializer.plainText().serialize(e.message());

        String target = skinsManager.getHeadTarget(player);

        String chatForm = "<head:" + target + ":true> " +
                "<white><" + player.getName() + "> " +
                text;

        Component mensajeFinal = mm.deserialize(chatForm);
        e.renderer((source, sourceDisplayName, message, viewer) -> mensajeFinal);
    }




}