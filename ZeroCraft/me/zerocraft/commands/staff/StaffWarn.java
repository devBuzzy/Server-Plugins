package me.zerocraft.commands.staff;

import me.zerocraft.extras.warn.Warn;
import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command: /warn<br>
 * Rank: MODERATOR
 */
public class StaffWarn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String labelString, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /warn <player> <reason>");
        } else if(args.length == 1) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /warn <player> <reason>");
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "That player was not found!");
            } else {
                Warn warn = new Warn();
                Player warnPlayer = Bukkit.getPlayer(args[0]);
                args[0] = "";
                String reason = CommandUtils.formatCast(args);
                warn.setup(warnPlayer, (Player) sender, reason);
                warn.run();
                warn = null;
            }
        }
        return true;
    }
}