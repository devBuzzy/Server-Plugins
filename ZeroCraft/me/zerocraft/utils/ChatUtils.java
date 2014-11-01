package me.zerocraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Chat utilities.
 */
public class ChatUtils {

    public static String word;

    public static String getWord() {
        return word;
    }

    public static String playerJoin(String p) {
        String joinMsg;
        joinMsg = ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;
        return joinMsg;
    }

    public static void broadcast(String m, Rank r) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(Rank.getRank(p, r)) {
                p.sendMessage(m);
            }
        }
    }

    public static String playerQuit(String p) {
        String joinMsg;
        joinMsg = ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p;
        return joinMsg;
    }

    public static void giveWord() {
        PlayerUtils.getBuilder().sendMessage(ChatColor.GREEN + "Your word is " + ChatColor.GRAY + PluginUtils.chars[1] + " " + ChatColor.DARK_PURPLE + getWord());
    }

    public static void setWord(String word, boolean give) {
        ChatUtils.word = word;
        if(give) giveWord();
        if(give) ChatUtils.broadcast(ChatColor.BLUE + "The word has been chosen!");
        if(give) PlayerUtils.getBuilder().playSound(PlayerUtils.getBuilder().getLocation(), Sound.CHEST_OPEN, 1, 1);
    }

    public static void clearChat(String clearer, String msg) {
        if(clearer == null && msg == null)
            throw new NullPointerException();
        for(int i = 0; i < 120; i++) {
            broadcast(" ");
        }
        if(msg != null) {
            broadcast(msg);
        } else {
            broadcast(ChatColor.GREEN + "Chat was cleared by staff member " + Rank.getFormattedName(Bukkit.getPlayerExact(clearer)) + ".");
        }
    }

    public static void broadcast(String message) {
        if(message == null) throw new NullPointerException();
        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(message);
        }
    }
}
