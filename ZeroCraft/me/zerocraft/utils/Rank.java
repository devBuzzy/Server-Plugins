package me.zerocraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Core rank file. Essentially manages all plugin user permissions in a nutshell.
 */
public enum Rank {

    /**
     * Owner rank for Zero
     */
    Owner,
    /**
     * Administrator staff rank
     */
    Admin,
    /**
     * Moderator staff rank
     */
    Mod,
    /**
     * Jr. Mod rank
     */
    JrMod,
    /**
     * Rank for VIP/Donor users
     */
    VIP,
    /**
     * Rank for Member users
     */
    Member;

    /**
     * Still a WIP.
     * @param sender The player in question
     * @return True if they are a member, false otherwise
     */
    public static boolean isMember(CommandSender sender) {
        if(!sender.hasPermission("zerocraft.rank.mod") && !sender.hasPermission("zerocraft.rank.admin") && !sender.getName().equalsIgnoreCase("THEE07") && !sender.getName().equalsIgnoreCase("CommonSense_64")) { //checks user ranks to see if they are a member
            return true;
        }
        return false;
    }

    /**
     * Returns a player's colored name
     * @param p - Player in question
     * @return The formatted name
     */
    public static String getFormattedName(Player p) {
        if (getRank(p) == Owner) {
            return ChatColor.DARK_BLUE + p.getName();
        } else if (getRank(p) == Admin) {
            return ChatColor.RED + p.getName();
        } else if (getRank(p) == Mod) {
            return ChatColor.DARK_GREEN + p.getName();
        } else if (getRank(p) == JrMod) {
            return ChatColor.GREEN + p.getName();
        } else if (getRank(p) == VIP) {
            return ChatColor.GOLD + p.getName();
        } else if (getRank(p) == Member) {
            return ChatColor.YELLOW + p.getName();
        } else {
            return ChatColor.YELLOW + p.getName();
        }
    }

    /**
     * Returns a player's colored name
     * @param p - Player in question
     * @return The formatted name
     */
    public static String getFormattedDisplayName(Player p) {
        if (getRank(p) == Owner) {
            return ChatColor.DARK_BLUE + p.getDisplayName();
        } else if (getRank(p) == Admin) {
            return ChatColor.RED + p.getDisplayName();
        } else if (getRank(p) == Mod) {
            return ChatColor.DARK_GREEN + p.getDisplayName();
        } else if (getRank(p) == JrMod) {
            return ChatColor.GREEN + p.getDisplayName();
        } else if (getRank(p) == VIP) {
            return ChatColor.GOLD + p.getDisplayName();
        } else if (getRank(p) == Member) {
            return ChatColor.YELLOW + p.getDisplayName();
        } else {
            return ChatColor.YELLOW + p.getDisplayName();
        }
    }

    /**
     * Get a player's rank.
     * @param sender - The player in question
     * @return The player's rank
     */
    public static Rank getRank(CommandSender sender) {
        if(sender.getName().equalsIgnoreCase("THEE07")) {
            // OWNER rank
            return Owner;
        } else if (sender.hasPermission("zerocraft.rank.admin") || sender.isOp()) {
            // ADMIN rank
            return Admin;
        } else if(sender.hasPermission("zerocraft.rank.mod")) {
            // MODERATOR rank
            return Mod;
        } else if (sender.hasPermission("zerocraft.rank.jrmod")) {
            // JRMOD rank [planned, not used yet as far as I know]
            return JrMod;
        } else if (sender.hasPermission("zerocraft.rank.vip")) {
            // VIP rank
            return VIP;
        } else if (sender.hasPermission("zerocraft.rank.member")) {
            // MEMBER rank
            return Member;
        } else {
            // GUEST rank (just to get the errors to shut up)
            return Member;
        }
    }

    /**
     * Check if a player is a specified rank.
     * @param sender - The player in question.
     * @param rank   - The rank to check for
     * @return True if the player has the correct rank, or false if not.
     */
    public static boolean getRank(CommandSender sender, Rank rank) {
        if(sender.getName().equalsIgnoreCase("THEE07") && rank == Owner) {
            return true;
        } else if(sender.hasPermission("zerocraft.rank.admin") && rank == Admin || sender.isOp() && rank == Admin) {
            return true;
        } else if(sender.hasPermission("zerocraft.rank.mod") && rank == Mod) {
            return true;
        } else if(sender.hasPermission("zerocraft.rank.jrmod") && rank == JrMod) {
            return true;
        } else if(sender.hasPermission("zerocraft.rank.vip") && rank == VIP) {
            return true;
        } else if(sender.hasPermission("zerocraft.rank.member") && rank == Member) {
            return true;
        }
        return false;
    }

    public static Rank fromString(String from) {
        if(from.equalsIgnoreCase("owner")) return Owner;
        else if(from.equalsIgnoreCase("admin") || from.equalsIgnoreCase("administrator")) return Admin;
        else if(from.equalsIgnoreCase("moderator")) return Mod;
        else if(from.equalsIgnoreCase("jrmod")) return JrMod;
        else if(from.equalsIgnoreCase("vip")) return VIP;
        else if(from.equalsIgnoreCase("member")) return Member;
        else return null;
    }

    /**
     * Sends a player a no permission message.
     * @param sender - The player in question
     * @param rank   - The rank needed for a command
     */
    public static void noPermissions(CommandSender sender, Rank rank) {
        if (rank == Owner) {
            sender.sendMessage(ChatColor.RED + "Minimum rank requred to run this command " + PluginUtils.chars[1] + ChatColor.DARK_BLUE + " OWNER");
        } else if (rank == Admin) {
            sender.sendMessage(ChatColor.RED + "Minimum rank requred to run this command " + PluginUtils.chars[1] + ChatColor.RED + " ADMINISTRATOR");
        } else if (rank == Mod) {
            sender.sendMessage(ChatColor.RED + "Minimum rank requred to run this command " + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " MODERATOR");
        } else if (rank == JrMod) {
            sender.sendMessage(ChatColor.RED + "Minimum rank requred to run this command " + PluginUtils.chars[1] + ChatColor.DARK_AQUA + " JR MOD");
        } else if (rank == VIP) {
            sender.sendMessage(ChatColor.RED + "Minimum rank requred to run this command " + PluginUtils.chars[1] + ChatColor.GOLD + " VIP");
        }
    }

}