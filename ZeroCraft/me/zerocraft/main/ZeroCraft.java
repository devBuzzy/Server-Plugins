package me.zerocraft.main;

import me.zerocraft.extras.buildmything.BuildMyThing;
import me.zerocraft.staffchat.SCType;
import me.zerocraft.staffchat.StaffChat;
import me.zerocraft.utils.LogUtils;
import me.zerocraft.utils.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core plugin file.
 */
public class ZeroCraft extends JavaPlugin {

    public static boolean debugMode = false;
    public static boolean consoleOutput = false;
    public static String broadcastTag = PluginUtils.msgNormal;

    public ZeroCraft() { }

    public void onEnable() {
        //First, register the configuration file to prevent NullPointerException's in all other features (Well.. at least hopefully)
        getConfig().options().copyDefaults(true);
        saveConfig();
        MainCommandArea.registerCommands(); //Register literally every command in the plugin
        Bukkit.getPluginManager().registerEvents(new ListenerFile(), this); //Register the core listeners
        Bukkit.getPluginManager().registerEvents(new StaffChat(), this); //Register the staff chat listener
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new BuildMyThing.MainTimer(), 20l, 20l); //start the Build My Thing timer
        PluginUtils.BMT.init(); //Initialize the Build My Thing game
        LogUtils.info("ZeroCraft > Plugin enabled!"); //Log to the console that the plugin is ready to use
    }

    public void onDisable() {
        SCType.clearStaffchatTypes(); //Clear the staff chats
        Bukkit.getScheduler().cancelTasks(this); //Stop all the tasks in the plugin
        LogUtils.info("ZeroCraft > Plugin enabled!"); //Log to the console that the plugin is now disabled
    }

}