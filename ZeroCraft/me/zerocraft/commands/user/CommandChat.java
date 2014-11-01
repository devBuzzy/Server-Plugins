package me.zerocraft.commands.user;

import me.zerocraft.staffchat.SCType;
import me.zerocraft.staffchat.StaffChat;
import me.zerocraft.utils.PlayerUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.naming.*;

/**
 * Command: /chat<br>
 * Aliases: /c, /channel, /sc<br>
 * Rank: [Varies]
 */
public class CommandChat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                            ChatColor.YELLOW + "The channels you may join are in GREEN. The ones you may not join are in RED."
                            + '\n'
                            + (Rank.getRank(sender, Rank.Admin) ? ChatColor.GREEN + "• ADMIN" : ChatColor.RED + "• ADMIN")
                            + '\n'
                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• MOD" : ChatColor.RED + "• MOD")
                            + '\n'
                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• STAFF" : ChatColor.RED + "• STAFF")
                            + '\n'
                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• ALERT" : ChatColor.RED + "• ALERT")
                            + '\n'
                            + (Rank.getRank(sender, Rank.VIP) ? ChatColor.GREEN + "• VIP" : ChatColor.RED + "• VIP")
                            + '\n'
                            + ChatColor.GREEN + "• BMT"
                            + '\n'
                            + ChatColor.GREEN + "• SHOW [on|off]"
                            + '\n'
                            + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• OFF [user]" : ChatColor.GREEN + "• OFF")
            );
            // That was a lot of code!
        } else {
            if(args[0].equalsIgnoreCase("admin")) {
                if(!Rank.getRank(sender, Rank.Admin)) {
                    Rank.noPermissions(sender, Rank.Admin);
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.ADMIN)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.ADMIN);
                        sender.sendMessage(PluginUtils.msgNormal + "Left ADMIN chat.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.ADMIN);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Joined ADMIN chat.");
                    }
                } else {
                    args[0] = ""; //nullify the channel name string so we can ignore it
                    String cast = "";
                    for(String s : args) {
                        if(s.equals("")) continue;
                        cast += s + " ";
                    }
                    StaffChat.staffChat(SCType.ADMIN, (Player) sender, cast);
                }
            } else if(args[0].equalsIgnoreCase("mod")) {
                if(!Rank.getRank(sender, Rank.Mod)) {
                    Rank.noPermissions(sender, Rank.Mod);
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.MOD)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.MOD);
                        sender.sendMessage(PluginUtils.msgNormal + "Left MODERATOR chat.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.MOD);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Joined MODERATOR chat.");
                    }
                } else {
                    args[0] = ""; //nullify the chat string
                    String cast = "";
                    for(String s : args) {
                        if(s.equals("")) continue;
                        cast = cast + s + " ";
                    }
                    StaffChat.staffChat(SCType.MOD, (Player) sender, cast);
                }
            } else if(args[0].equalsIgnoreCase("staff")) {
                if(!Rank.getRank(sender, Rank.Mod)) {
                    Rank.noPermissions(sender, Rank.Mod);
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.STAFF)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.STAFF);
                        sender.sendMessage(PluginUtils.msgNormal + "Left STAFF chat.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.STAFF);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Joined STAFF chat.");
                    }
                } else {
                    args[0] = ""; //nullify the chat string
                    String cast = "";
                    for(String s : args) {
                        if(s.equals("")) continue;
                        cast = cast + s + " ";
                    }
                    StaffChat.staffChat(SCType.STAFF, (Player) sender, cast);
                }
            } else if(args[0].equalsIgnoreCase("alert")) {
                if(!Rank.getRank(sender, Rank.Mod)) {
                    Rank.noPermissions(sender, Rank.Mod);
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.ALERT)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.ALERT);
                        sender.sendMessage(PluginUtils.msgNormal + "Stopped broadcasting alerts.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.ALERT);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Started broadcasting alerts.");
                    }
                } else {
                    sender.sendMessage(ChatColor.BLUE + "Use /say. I'm not moving that command over here.");
                }
            } else if(args[0].equalsIgnoreCase("vip")) {
                if(!Rank.getRank(sender, Rank.VIP)) {
                    Rank.noPermissions(sender, Rank.VIP);
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.VIP)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.VIP);
                        sender.sendMessage(PluginUtils.msgNormal + "Left VIP chat.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.VIP);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Joined VIP chat.");
                    }
                } else {
                    args[0] = ""; //nullify the chat string
                    String cast = "";
                    for(String s : args) {
                        if(s.equals("")) continue;
                        cast = cast + s + " ";
                    }
                    StaffChat.staffChat(SCType.VIP, (Player) sender, cast);
                }
            } else if(args[0].equalsIgnoreCase("bmt")) {
                if(((Player) sender).getWorld() != Bukkit.getWorld("arena")) {
                    sender.sendMessage(ChatColor.RED + "Can only join the BMT channel in the Build My Thing area.");
                    return true;
                }
                if(!PluginUtils.BMT.enabled) {
                    sender.sendMessage(ChatColor.RED + "BMT must be enabled to join the channel!");
                    return true;
                }
                if(args.length == 1) {
                    if(SCType.hasStaffchat(((Player) sender).getUniqueId(), SCType.BMT)) {
                        SCType.removeUser(((Player) sender).getUniqueId(), SCType.BMT);
                        sender.sendMessage(PluginUtils.msgNormal + "Left BMT channel.");
                    } else {
                        try {
                            SCType.addUser(((Player) sender).getUniqueId(), SCType.BMT);
                        } catch (OperationNotSupportedException ignore) {}
                        sender.sendMessage(PluginUtils.msgNormal + "Joined BMT channel.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "No. I am not supporting a message to the BMT channel via command. Ever.");
                }
            } else if(args[0].equalsIgnoreCase("show")) {
                if(sender.getName().equalsIgnoreCase("THEE07")) {
                    if(PlayerUtils.zeroStaff) {
                        PlayerUtils.zeroStaff = false;
                        sender.sendMessage(ChatColor.BLUE + "Toggled channel view" + ChatColor.RED + " off.");
                    } else {
                        PlayerUtils.zeroStaff = true;
                        sender.sendMessage(ChatColor.BLUE + "Toggled channel view" + ChatColor.GREEN + " on.");
                    }
                    return true;
                }
                if(!SCType.canSeeStaffchat(((Player) sender).getUniqueId())) {
                    SCType.setCanSeeStaffchat(((Player) sender).getUniqueId(), true);
                    sender.sendMessage(ChatColor.BLUE + "Toggled channel view on.");
                } else {
                    SCType.setCanSeeStaffchat(((Player) sender).getUniqueId(), false);
                    sender.sendMessage(ChatColor.BLUE + "Toggled channel view off.");
                }
            } else if(args[0].equalsIgnoreCase("off")) {
                if(args.length == 1) {
                    SCType.clearStaffchat(((Player) sender).getUniqueId());
                    sender.sendMessage(ChatColor.YELLOW + "Left all channels.");
                } else if(args.length >= 3) {
                    SCType.clearStaffchat(((Player) sender).getUniqueId());
                    sender.sendMessage(ChatColor.YELLOW + "Left all channels.");
                } else {
                    if(!Rank.getRank(sender, Rank.Mod)) {
                        SCType.clearStaffchat(((Player) sender).getUniqueId());
                        sender.sendMessage(ChatColor.YELLOW + "Left all channels.");
                        return true;
                    }
                    if(Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(ChatColor.RED + "That player was not found!");
                    } else {
                        SCType.clearStaffchat(Bukkit.getPlayer(args[1]).getUniqueId());
                        sender.sendMessage(ChatColor.YELLOW + "Forced player " + ChatColor.RED + Bukkit.getPlayer(args[1]).getName() + ChatColor.YELLOW + " to leave all channels.");
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED + "Forced to leave all channels by staff member " + sender.getName() + ".");
                    }
                }
            } else {
                sender.sendMessage(
                                ChatColor.YELLOW + "The channels you may join are in GREEN. The ones you may not join are in RED."
                                + '\n'
                                + (Rank.getRank(sender, Rank.Admin) ? ChatColor.GREEN + "• ADMIN" : ChatColor.RED + "• ADMIN")
                                + '\n'
                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• MOD" : ChatColor.RED + "• MOD")
                                + '\n'
                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• STAFF" : ChatColor.RED + "• STAFF")
                                + '\n'
                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• ALERT" : ChatColor.RED + "• ALERT")
                                + '\n'
                                + (Rank.getRank(sender, Rank.VIP) ? ChatColor.GREEN + "• VIP" : ChatColor.RED + "• VIP")
                                + '\n'
                                + ChatColor.GREEN + "• BMT"
                                + '\n'
                                + ChatColor.GREEN + "• SHOW [on|off]"
                                + '\n'
                                + (Rank.getRank(sender, Rank.Mod) ? ChatColor.GREEN + "• OFF [user]" : ChatColor.GREEN + "• OFF")
                );
                // That was a lot of code!
            }
        }
        return true;
    }

}