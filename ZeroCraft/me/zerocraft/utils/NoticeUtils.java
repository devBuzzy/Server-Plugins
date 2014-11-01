package me.zerocraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * Notice utilities.
 */
public class NoticeUtils {

    static Plugin pl = Bukkit.getPluginManager().getPlugin("ZeroCraft");

    static String noticeOne;
    static String noticeTwo;
    static String noticeThree;
    static String noticeFour;

    public static String getNotice(int num, boolean random) {
        if(random) {
            Random r = new Random();
            int nu = r.nextInt(4) + 1;
            switch (nu) {
                case 1:
                    return noticeOne;
                case 2:
                    return noticeTwo;
                case 3:
                    return noticeThree;
                case 4:
                    return noticeFour;
            }
        } else {
            if(num == 1) {
                return noticeOne;
            } else if(num == 2) {
                return noticeTwo;
            } else if(num == 3) {
                return noticeThree;
            } else if(num == 4) {
                return noticeFour;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return null;
    }

    public static void setupNotices() {
        noticeOne = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("notice.one"));
        noticeTwo = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("notice.two"));
        noticeThree = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("notice.three"));
        noticeFour = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("notice.four"));
    }

    public static void setNotice(int notice, String noticeMsg) {
        if(notice == 1) {
            noticeOne = noticeMsg;
        } else if(notice == 2) {
            noticeTwo = noticeMsg;
        } else if(notice == 3) {
            noticeThree = noticeMsg;
        } else if(notice == 4) {
            noticeFour = noticeMsg;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}