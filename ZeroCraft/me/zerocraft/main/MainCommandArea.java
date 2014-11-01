package me.zerocraft.main;

import me.zerocraft.commands.staff.StaffClearChat;
import me.zerocraft.commands.staff.StaffFreeze;
import me.zerocraft.commands.staff.StaffGlobalMute;
import me.zerocraft.commands.staff.StaffInfo;
import me.zerocraft.commands.staff.StaffSay;
import me.zerocraft.commands.staff.StaffWarn;
import me.zerocraft.commands.user.CommandChat;
import me.zerocraft.commands.user.CommandTutorial;
import me.zerocraft.commands.vip.VIPFirework;
import me.zerocraft.extras.buildmything.BuildMyThing;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Registers all the commands and such
 */
public class MainCommandArea {

    static List<String> firework = new ArrayList<>();
    static List<String> buildmything = new ArrayList<>();
    static List<String> vanish = new ArrayList<>();
    static List<String> chat = new ArrayList<>();
    static List<String> clearchat = new ArrayList<>();

    public static void registerCommands() {
        registerAliases(); //Register the aliases for all commands before going any further
        // Actual commands
        Bukkit.getPluginCommand("info").setExecutor(new StaffInfo()); //Info command
        Bukkit.getPluginCommand("say").setExecutor(new StaffSay()); //Say command
        Bukkit.getPluginCommand("buildmything").setExecutor(new BuildMyThing.Command()); //Buildmything command [/bmt]
        Bukkit.getPluginCommand("firework").setExecutor(new VIPFirework()); //Firework command [/fw]
        Bukkit.getPluginCommand("tutorial").setExecutor(new CommandTutorial()); //Tutorial command
        Bukkit.getPluginCommand("clearchat").setExecutor(new StaffClearChat()); //Clearchat command [/cc]
        Bukkit.getPluginCommand("chat").setExecutor(new CommandChat()); //Chat command [/c, /channel, /sc]
        Bukkit.getPluginCommand("globalmute").setExecutor(new StaffGlobalMute()); //Globalmute command [/gmute]
        Bukkit.getPluginCommand("freeze").setExecutor(new StaffFreeze()); //Freeze command
        Bukkit.getPluginCommand("warn").setExecutor(new StaffWarn()); //Warn command
        // Command aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("vanish").setAliases(vanish); //Vanish alias
        Bukkit.getPluginCommand("buildmything").setAliases(buildmything); //Buildmything alias
        Bukkit.getPluginCommand("firework").setAliases(firework); //Firework alias
    }

    public static void registerAliases() {
        firework.add("fw");
        clearchat.add("cc");
        chat.add("sc");
        vanish.add("v");
        chat.add("channel");
        chat.add("c");
        buildmything.add("bmt");
    }

}