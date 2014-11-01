package me.zerocraft.staffchat;

import me.zerocraft.utils.ChatUtils;
import me.zerocraft.utils.PlayerUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * "Frontend" code for the channel system
 */
public class StaffChat implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerChat(AsyncPlayerChatEvent ev) {
        if (SCType.getType(ev.getPlayer().getUniqueId()) != null) {
            try {
                staffChat(SCType.getType(ev.getPlayer().getUniqueId()), ev.getPlayer(), ChatColor.translateAlternateColorCodes('&', ev.getMessage()));
            } catch(NullPointerException ex) {
                notice("The player " + ev.getPlayer().getName() + " has their channel set to something the staff chat method does not recognize.", "Staff Chat");
                notice("The channel in question: " + SCType.getType(ev.getPlayer().getUniqueId()), "Staff Chat");
                SCType.clearStaffchat(ev.getPlayer().getUniqueId());
            } finally {
                ev.setCancelled(true); //finally set the event to be cancelled regardless of if the type was found in the staff chat method or not
            }
        }
    }

    public static void staffChat(SCType type, Player p, String msg) {
        if(type == SCType.BMT) {
            BMT(msg, p); //redirect to BMT method
            return;
        }
        if(type == SCType.ALERT) {
            alert(msg, p); //Redirect to Alert method
            return;
        }
        if(type == SCType.NOTIFICATION) {
            SCType.clearStaffchat(p.getUniqueId());
            throw new NullPointerException(); //I do not support any players in the notification channel. No clue how they did this, but throw an exception anyways
        }
        if(type == SCType.ADMIN) {
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(!Rank.getRank(pl, Rank.Admin) || pl.getName().equalsIgnoreCase("THEE07") && !PlayerUtils.zeroStaff || SCType.noStaffchat.contains(pl.getUniqueId()))
                    continue; //Continue along the players if the user is not an admin, or is Zero
                pl.sendMessage(ChatColor.YELLOW + "Admin Chat" + ChatColor.BLUE + " » " + ChatColor.RED + " " + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.DARK_RED + " " + msg);
            }
        } else if(type == SCType.MOD) {
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(!Rank.getRank(pl, Rank.Mod) || pl.getName().equalsIgnoreCase("THEE07") && !PlayerUtils.zeroStaff || SCType.noStaffchat.contains(pl.getUniqueId()))
                    continue; //Continue along the players if the user is not a moderator, or is Zero
                pl.sendMessage(ChatColor.BLUE + "[" + ChatColor.DARK_GREEN + "Mod Chat" + ChatColor.BLUE + "]" + ChatColor.RED + " " + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.LIGHT_PURPLE + " " + msg);
            }
        } else if(type == SCType.STAFF) {
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(!Rank.getRank(pl, Rank.Mod) || pl.getName().equalsIgnoreCase("THEE07") && !PlayerUtils.zeroStaff || SCType.noStaffchat.contains(pl.getUniqueId()))
                    continue; //Continue along the players if the user is not a moderator, or is Zero
                pl.sendMessage(ChatColor.BLUE + "[" + ChatColor.DARK_PURPLE + "Staff Chat" + ChatColor.BLUE + "]" + ChatColor.RED + " " + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.YELLOW + " " + msg);
            }
        } else if(type == SCType.VIP) {
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(!Rank.getRank(pl, Rank.VIP) || pl.getName().equalsIgnoreCase("THEE07") && !PlayerUtils.zeroStaff || SCType.noStaffchat.contains(pl.getUniqueId()))
                    continue; //Continue along the players if the user is not a VIP, or is Zero
                pl.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "VIP Chat" + ChatColor.BLUE + "] " + ChatColor.RED + " " + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.YELLOW + " " + msg);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public static void alert(String msg, Player p) {
        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(ChatColor.RED + "" + Rank.getRank(p) + " " + Rank.getFormattedDisplayName(p) + ": " + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public static void notice(String msg, String notifyType) {
        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(!Rank.getRank(pl, Rank.Mod)) continue;
            pl.sendMessage(ChatColor.LIGHT_PURPLE + "[Notification] " + ChatColor.RED + notifyType + ChatColor.GRAY + " " + PluginUtils.chars[1] + ChatColor.YELLOW + " " + msg);
        }
    }

    public static void BMTNotify(String msg) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!SCType.canSeeStaffchat(p.getUniqueId()) && Rank.getRank(p, Rank.Mod)) {
                p.sendMessage(ChatColor.BLUE + "[BMT Notification] " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.RED + " " + msg);
            }
        }
    }

    public static void BMT(String m, Player p) {
        String msg = m.toLowerCase();
        if(PlayerUtils.getBuilder() == null || ChatUtils.getWord() == null || !PluginUtils.BMT.enabled) {
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(SCType.getType(pl.getUniqueId()) == SCType.BMT) {
                    pl.sendMessage(ChatColor.LIGHT_PURPLE + "[BMT] " + ChatColor.BLUE + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + " §a" + m);
                }
            }
        } else {
            if(msg.contains(ChatUtils.getWord())) {
                if(PlayerUtils.getBuilder().equals(p)) {
                    p.sendMessage(ChatColor.DARK_PURPLE + "You may not give out the word!"); //tell the builder they may not give the word out
                } else {
                    if(PluginUtils.BMT.players.contains(p.getName())) {
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "You may not give out the word!"); //tell the player they may not give out the word
                        return; //don't do anything if the user already got the word
                    }
                    ChatUtils.broadcast(ChatColor.LIGHT_PURPLE + "[BMT] " + ChatColor.YELLOW + Rank.getFormattedName(p) + ChatColor.LIGHT_PURPLE + " has guessed the word correctly!");
                    PluginUtils.BMT.players.add(p.getName()); //add the user to the p arraylist
                }
            } else {
                for(Player pl : Bukkit.getOnlinePlayers()) {
                    if (SCType.getType(pl.getUniqueId()) == SCType.BMT) {
                        pl.sendMessage(ChatColor.LIGHT_PURPLE + "[BMT] " + ChatColor.BLUE + p.getDisplayName() + " " + ChatColor.GRAY + PluginUtils.chars[1] + " §a" + m);
                    }
                }
            }
        }
    }

}