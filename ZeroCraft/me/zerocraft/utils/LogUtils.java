package me.zerocraft.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Log utilities file. Basically just manages all the logging things and such.
 */
public class LogUtils {

    public static void log(Level level, String message) {
        Bukkit.getLogger().log(level, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void warn(String message) {
        log(Level.WARNING, message);
    }

    public static void severe(String message) {
        log(Level.SEVERE, message);
    }

}