package me.zerocraft.commands.staff;

import me.zerocraft.utils.ChatUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command: /gmute<br>
 * Rank: MODERATOR
 */
public class StaffGlobalMute implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(PluginUtils.globalMute) {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.RED + "Staff member " + ChatColor.GREEN + ((Player) sender).getDisplayName() + ChatColor.RED + " has lifted the global mute.");
            ChatUtils.broadcast(" ");
            PluginUtils.globalMute = false;
        } else {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.RED + "Staff member " + ChatColor.GREEN + ((Player) sender).getDisplayName() + ChatColor.RED + " has globally muted the chat.");
            ChatUtils.broadcast(" ");
            PluginUtils.globalMute = true;
        }
        return true;
    }

}