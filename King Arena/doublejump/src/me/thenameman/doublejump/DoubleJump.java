//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.thenameman.doublejump;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DoubleJump extends JavaPlugin implements Listener {

    public DoubleJump() {
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("doublejump.use")) {
            if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.SPONGE && !player.isFlying()) {
                player.setVelocity(player.getLocation().getDirection().multiply(1.5D).setY(1));
            } else if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.REDSTONE_BLOCK && !player.isFlying()) {
                player.setVelocity(player.getLocation().getDirection().multiply(2.0D).setY(1));
            } else if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.GOLD_BLOCK && !player.isFlying()) {
                player.setVelocity(player.getLocation().getDirection().multiply(1.0D).setY(1));
            } else if(player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.DIAMOND_BLOCK && !player.isFlying()) {
                player.setVelocity(player.getLocation().getDirection().multiply(3.5D).setY(1));
            }
        }

    }
}
