package me.zerocraft.commands.staff;

import me.zerocraft.utils.ChatUtils;
import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command: /clearchat<br>
 * Alias: /cc<br>
 * Rank: MODERATOR
 */
public class StaffClearChat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        String cast = CommandUtils.formatCast(args);
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            ChatUtils.clearChat(sender.getName(), null);
        } else {
            ChatUtils.clearChat(null, ChatColor.translateAlternateColorCodes('&', ChatColor.BLUE + ((Player) sender).getDisplayName() + " » " + ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', cast)));
        }
        return true;
    }

}