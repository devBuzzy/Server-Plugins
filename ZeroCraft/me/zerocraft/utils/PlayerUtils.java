package me.zerocraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Player utilities file.
 */
public class PlayerUtils {

    private static Player player;
    public static boolean zeroStaff = false;

    public static Player getBuilder() {
        return player;
    }

    public static void setBuilder(Player builder) {
        player = builder;
        if(PluginUtils.BMT.enabled) {
            ChatUtils.broadcast(ChatColor.BLUE + "Builder this round " + ChatColor.GRAY + PluginUtils.chars[1] + " " + ChatColor.LIGHT_PURPLE + getBuilder().getDisplayName());
        }
    }

}