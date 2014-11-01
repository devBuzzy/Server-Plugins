package me.zerocraft.commands.staff;

import me.zerocraft.staffchat.SCType;
import me.zerocraft.staffchat.StaffChat;
import me.zerocraft.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Command: /say<br>
 * Rank: MODERATOR
 */
public class StaffSay implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        String cast = CommandUtils.formatCast(args);
        if(sender instanceof ConsoleCommandSender) {
            return true;
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /say <message>");
        } else {
            StaffChat.staffChat(SCType.ALERT, (Player) sender, ChatColor.translateAlternateColorCodes('&', cast));
        }

        return true;
    }
}
