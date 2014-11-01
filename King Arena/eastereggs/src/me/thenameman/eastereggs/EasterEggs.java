//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.thenameman.eastereggs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EasterEggs extends JavaPlugin {

    public EasterEggs() {
    }

    public void onDisable() {
    }

    public void onEnable() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cast = "";
        //Slightly optimized before commiting this - this for loop isn't the actual source code. Real source;
        /*
        String cast = "";
        String[] var9 = args;
        int var8 = args.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            String player = var9[var7];
            cast = cast + player + " ";
        }
         */
        for(String s : args) {
            cast = cast + (cast.equals("") ? "" : " ") + s;
        }
        Player var10 = (Player)sender;
        if(label.equalsIgnoreCase("boshy")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.RED + "I WANNA BE THE " + ChatColor.GOLD + "BOSHY");
        } else if(label.equalsIgnoreCase("pokemon")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.GOLD + "Gotta catch \'em all!");
        } else if(label.equalsIgnoreCase("villager")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.RED + "Mehhh " + ChatColor.GOLD + "-Villager, 2013 and 2014 and 2015 and 2016 and 2017 and 2018 and 2019 and 2020. And 2021.");
        } else if(label.equalsIgnoreCase("lag")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs]" + ChatColor.RED + "Lag be hating everyone.");
        } else if(label.equalsIgnoreCase("ads")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.RED + "Every time politic ads come on, I prefer to throw my computer off a bridge." + ChatColor.GOLD + "-TheNameMan, 3/28/2014");
        } else if(label.equalsIgnoreCase("console")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.RED + "Console be OP. " + ChatColor.GOLD + "-TheNameMan, ???");
        } else if(label.equalsIgnoreCase("feelz")) {
            var10.sendMessage(ChatColor.BLUE + "[EasterEggs] " + ChatColor.RED + "da feelz are op");
        }

        return false;
    }
}
