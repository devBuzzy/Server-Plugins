//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.thenameman.announce;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    public PlayerListener() {
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage((String)null);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage((String)null);
    }
}
