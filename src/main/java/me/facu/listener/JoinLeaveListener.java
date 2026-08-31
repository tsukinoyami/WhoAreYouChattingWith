package me.facu.listener;

import me.facu.WhoAreYouChattingWith;
import me.facu.util.SkinsManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final SkinsManager skinsManager;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public JoinLeaveListener(SkinsManager skinsManager) {
        this.skinsManager = skinsManager;
    }

    boolean joinLeaveHeads = WhoAreYouChattingWith.returnSelf().getConfig().getBoolean("join-leave-heads");

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        String target = skinsManager.getHeadTarget(player);

        if (joinLeaveHeads) {
            Component originalMessage = e.joinMessage();
            if (originalMessage != null) {
                Component headComponent = mm.deserialize("<white><head:" + target + ":true></white><yellow> ");
                Component finalMessage = originalMessage.replaceText(builder -> {
                    builder.matchLiteral(player.getName())
                            .replacement(headComponent.append(Component.text(player.getName())));
                });
                e.joinMessage(finalMessage);
            }
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        String target = skinsManager.getHeadTarget(player);

        if (joinLeaveHeads){
            Component originalMessage = e.quitMessage();
            if (originalMessage != null) {
                Component headComponent = mm.deserialize("<white><head:" + target + ":true></white><yellow> ");
                Component finalMessage = originalMessage.replaceText(builder -> {
                    builder.matchLiteral(player.getName())
                            .replacement(headComponent.append(Component.text(player.getName())));
                });
                e.quitMessage(finalMessage);
            }
        }
    }
}
