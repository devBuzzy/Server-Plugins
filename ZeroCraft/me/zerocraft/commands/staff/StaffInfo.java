package me.zerocraft.commands.staff;

import me.zerocraft.staffchat.SCType;
import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command: /info<br>
 * Rank: MODERATOR
 */
public class StaffInfo implements CommandExecutor {

    char[] chars = PluginUtils.chars;

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        if (!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /info <user>");
        } else if (args.length >= 3) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /info <user>");
        } else {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player p = Bukkit.getPlayer(args[0]);
                sender.sendMessage(CommandUtils.formattedCmd("Info: " + p.getName(), false));
                sender.sendMessage(ChatColor.YELLOW + "User rank " + chars[1] + ChatColor.GREEN + " " + Rank.getRank(p));
                sender.sendMessage(ChatColor.YELLOW + "Staff chat channel " + chars[1] + ChatColor.GREEN + " " + SCType.getType(p.getUniqueId()));
                sender.sendMessage(ChatColor.YELLOW + "Is frozen " + chars[1] + ChatColor.GREEN + " " + PluginUtils.frozenPlayers.contains(p.getUniqueId()));
                sender.sendMessage(ChatColor.YELLOW + "Food level " + chars[1] + ChatColor.GREEN + " " + p.getFoodLevel());
                sender.sendMessage(ChatColor.YELLOW + "Saturation level " + chars[1] + ChatColor.GREEN + " " + p.getSaturation());
                sender.sendMessage(ChatColor.YELLOW + "Display name " + chars[1] + ChatColor.GREEN + " " + p.getDisplayName());
                sender.sendMessage(ChatColor.YELLOW + "Gamemode " + chars[1] + ChatColor.GREEN + " " + p.getGameMode());
                sender.sendMessage(ChatColor.YELLOW + "UUID " + chars[1] + ChatColor.GREEN + " " + p.getUniqueId());
                sender.sendMessage(ChatColor.YELLOW + "Level " + chars[1] + ChatColor.GREEN + " " + p.getLevel());
                sender.sendMessage(ChatColor.YELLOW + "Speed; WALK " + chars[1] + ChatColor.GREEN + " " + p.getWalkSpeed());
                sender.sendMessage(ChatColor.YELLOW + "Speed; FLY " + chars[1] + ChatColor.GREEN + " " + p.getFlySpeed());
                sender.sendMessage(CommandUtils.formattedCmd("Info: " + p.getName(), false));
            } else {
                sender.sendMessage(ChatColor.RED + "Player \"" + args[0] + "\" was not found!"); //the player was not found
            }
        }
        return true;
    }

}