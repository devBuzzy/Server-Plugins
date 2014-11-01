package me.zerocraft.commands.vip;

import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command: /firework<br>
 * Alias: /fw<br>
 * Rank: VIP
 */
public class VIPFirework implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        CommandUtils.shootFireworks(sender);
        sender.sendMessage(ChatColor.BLUE + "Launched a firework!");
        return true;
    }


}