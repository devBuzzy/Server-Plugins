package me.zerocraft.main;

import me.zerocraft.staffchat.SCType;
import me.zerocraft.staffchat.StaffChat;
import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.PlayerUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Literally almost [every] listener in the plugin.
 */
public class ListenerFile implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinEvent ev) {
        ev.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));
        if (!ev.getPlayer().hasPlayedBefore()) {
            ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Welcome to ZeroCraft, " + ev.getPlayer().getPlayerListName() + ". For some starter tutorials, do /tutorial.");
            CommandUtils.shootFireworks(ev.getPlayer());
            CommandUtils.shootFireworks(ev.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerInteract(PlayerInteractEvent ev) {
        if (!(ev.getPlayer() instanceof Player)) return; //just checking, if not a player don't check if frozen
        if (PluginUtils.frozenPlayers.contains(ev.getPlayer().getUniqueId())) {
            ev.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerEntityInteract(PlayerInteractEntityEvent ev) {
        if (!(ev.getPlayer() instanceof Player)) return; //just checking, if not a player don't check if frozen
        if (PluginUtils.frozenPlayers.contains(ev.getPlayer().getUniqueId())) {
            ev.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerDrop(PlayerDropItemEvent ev) {
        if (!(ev.getPlayer() instanceof Player)) return; //making sure it is a player
        if (PluginUtils.frozenPlayers.contains(ev.getPlayer().getUniqueId())) {
            ev.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void explosion(EntityExplodeEvent ev) {
        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLeave(PlayerQuitEvent ev) {
        Player p = ev.getPlayer();
        if (Rank.getRank(p) == Rank.Owner)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_BLUE + p.getName());
        else if (Rank.getRank(p) == Rank.Admin)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.RED + p.getName());
        else if (Rank.getRank(p) == Rank.Mod)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_GREEN + p.getName());
        else if (Rank.getRank(p) == Rank.JrMod)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GREEN + p.getName());
        else if (Rank.getRank(p) == Rank.VIP)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getName());
        else if (Rank.getRank(p) == Rank.Member)
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p.getName());
        else
            ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p.getName());
        if (SCType.getType(ev.getPlayer().getUniqueId()) != null)
            SCType.clearStaffchat(ev.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void playerWater(PlayerInteractEvent ev) {
        if (ev.getItem() == new ItemStack(Material.WATER_BUCKET) && ev.getAction() == Action.RIGHT_CLICK_BLOCK && !Rank.getRank(ev.getPlayer(), Rank.Mod) && ev.getPlayer().getWorld() == Bukkit.getWorld("creative")) {
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(ChatColor.DARK_RED + "Permission denied.");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerKicked(PlayerKickEvent ev) {
        if (ev.getLeaveMessage() == null) {
            Player p = ev.getPlayer();
            if (Rank.getRank(p) == Rank.Owner)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_BLUE + p.getName());
            else if (Rank.getRank(p) == Rank.Admin)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.RED + p.getName());
            else if (Rank.getRank(p) == Rank.Mod)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_GREEN + p.getName());
            else if (Rank.getRank(p) == Rank.JrMod)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GREEN + p.getName());
            else if (Rank.getRank(p) == Rank.VIP)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getName());
            else if (Rank.getRank(p) == Rank.Member)
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p.getName());
            else
                ev.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + p.getName());
        }
        if (SCType.getType(ev.getPlayer().getUniqueId()) != null)
            SCType.clearStaffchat(ev.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerLogin(PlayerLoginEvent ev) {
        Player p = ev.getPlayer();
        if (ev.getPlayer().getName().contains(" ")) {
            ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Invalid username."); //filter them out due to their name having a space in it, to keep them from bypassing commands
            StaffChat.notice(ChatColor.RED + "Player \"" + p.getName() + "\" tried to join with an invalid name.", "Join Filter");
        }
    }

    @EventHandler
    public void mobSpawn(CreatureSpawnEvent ev) {
        if (ev.getEntity() instanceof Chicken) {
            if (ev.getLocation().getWorld() == Bukkit.getWorld("creative")) ev.setCancelled(true);
        } else if (ev.getEntity() instanceof Snowman) {
            if (ev.getLocation().getWorld() == Bukkit.getWorld("creative")) ev.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerThrow(ProjectileLaunchEvent ev) {
        if (ev.getEntity().getWorld() == Bukkit.getWorld("creative")) {
            ev.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerChat(AsyncPlayerChatEvent ev) {
        if (PluginUtils.globalMute) {
            if (Rank.getRank(ev.getPlayer(), Rank.Mod) || PluginUtils.gmuteBypass.contains(ev.getPlayer().getName())) {
                return;
            }
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(ChatColor.RED + "Chat is globally muted!");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void chatFormat(AsyncPlayerChatEvent ev) {
        if (ev.getMessage() == null || ChatColor.stripColor(ev.getMessage()).equals("")) {
            ev.setCancelled(true);
            return;
        }
        ev.setFormat(ChatColor.GRAY + "[" + Rank.getRank(ev.getPlayer()) + "] " + Rank.getFormattedDisplayName(ev.getPlayer()) + ChatColor.GRAY + " " + PluginUtils.chars[1] + ChatColor.WHITE + " " + (Rank.getRank(ev.getPlayer(), Rank.Mod) ? ChatColor.translateAlternateColorCodes('&', ev.getMessage()) : ev.getMessage()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerCommand(PlayerCommandPreprocessEvent ev) {
        String cmd = ev.getMessage();
        if (Rank.getRank(ev.getPlayer(), Rank.Admin)) {
            return;
        }
        if (cmd.toLowerCase().startsWith("/bukkit:?") || cmd.toLowerCase().startsWith("/bukkit:help") || cmd.startsWith("/?")) {
            // /? and /bukkit:help commands blocked
            ev.setCancelled(true);
            ev.getPlayer().performCommand("help");
            ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Redirected to /help.");
        } else if (cmd.toLowerCase().startsWith("/pl") || cmd.toLowerCase().startsWith("/bukkit:pl") || cmd.toLowerCase().startsWith("/plugins") || cmd.toLowerCase().startsWith("/bukkit:plugins")) {
            // /pl and /plugins commands blocked
            ev.setCancelled(true);
            //StaffChat.staffChat("Player " + ev.getPlayer().getDisplayName() + " is suspected of viewing the plugins!", StaffChatType.NOTIFICATIONS);
        } else if (cmd.toLowerCase().startsWith("/bukkit:ver") || cmd.toLowerCase().startsWith("/bukkit:version") || cmd.toLowerCase().startsWith("/version") || cmd.toLowerCase().startsWith("/ver") || cmd.toLowerCase().startsWith("/bukkit:about ") || cmd.toLowerCase().startsWith("/about ")) {
            // /ver and /version commands blocked
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(PluginUtils.msgNormal + "You are a naughty boy (or girl)!");
        } else if (cmd.toLowerCase().startsWith("/bukkit:me")) {
            // bukkit:me command blocked
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Builder, don't even try this command.");
        }
    }

}