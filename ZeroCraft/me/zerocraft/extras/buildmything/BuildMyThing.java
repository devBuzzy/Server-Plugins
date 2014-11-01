package me.zerocraft.extras.buildmything;

import me.zerocraft.staffchat.SCType;
import me.zerocraft.staffchat.StaffChat;
import me.zerocraft.utils.ChatUtils;
import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.PlayerUtils;
import me.zerocraft.utils.PluginUtils;
import me.zerocraft.utils.Rank;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Core Build My Thing file.
 */
public class BuildMyThing {

    /**
     * Core Build My Thing command
     */
    public static class Command implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command label, String stringLabel, String[] args) {
            if(args.length == 0) {
                sender.sendMessage(CommandUtils.formattedCmd("Build My Thing", true));
                if(Rank.getRank(sender, Rank.Mod)) {
                    sender.sendMessage("/bmt game " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " Enabling the game");
                    sender.sendMessage("/bmt round " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " Start a new round");
                }
                sender.sendMessage("/bmt info " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.YELLOW + " Get current game info");
                if(sender.getName().equals("TheNameMan") || sender.getName().equals("CommonSense_64")) {
                    sender.sendMessage("/bmt dev " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.LIGHT_PURPLE + " Enable/disable BMT dev mode");
                }
                sender.sendMessage(CommandUtils.formattedCmd("Build My Thing", true));
            } else {
                if(args[0].equalsIgnoreCase("game")) {
                    if(!Rank.getRank(sender, Rank.Mod)) {
                        Rank.noPermissions(sender, Rank.Mod);
                        return true;
                    }
                    if(args.length == 1) {
                        if(PluginUtils.BMT.enabled) {
                            PluginUtils.BMT.enabled = false;
                            ChatUtils.broadcast(ChatColor.YELLOW + "BMT " + PluginUtils.chars[1] + " The Build My Thing minigame is now " + ChatColor.RED + "ENDED!" + ChatColor.GRAY + " The word was: " + ChatColor.LIGHT_PURPLE + ChatUtils.getWord());
                            ChatUtils.setWord(null, false);
                            PlayerUtils.setBuilder(null);
                            PluginUtils.BMT.players.clear();
                        } else {
                            PluginUtils.BMT.enabled = true;
                            ChatUtils.broadcast(ChatColor.YELLOW + "BMT " + PluginUtils.chars[1] + " The Build My Thing minigame is now " + ChatColor.GREEN + "STARTED!");
                        }
                    } else {
                        boolean enable;
                        try {
                            enable = Boolean.parseBoolean(args[1]); //try to parse the boolean
                        } catch (Exception ex) {
                            sender.sendMessage(ChatColor.BLUE + "Usage: /bmt game [true|false]"); //it isn't a boolean so just complain to the person
                            return true;
                        }
                        if (enable) {
                            PluginUtils.BMT.enabled = true;
                            ChatUtils.broadcast(ChatColor.YELLOW + "[BMT] The Build My Thing minigame is now " + ChatColor.GREEN + "STARTED!");
                        } else {
                            PluginUtils.BMT.enabled = false;
                            ChatUtils.broadcast(ChatColor.YELLOW + "[BMT] The Build My Thing minigame is now " + ChatColor.RED + "ENDED!" + ChatColor.GRAY + " The word was: " + ChatColor.LIGHT_PURPLE + ChatUtils.getWord());
                            ChatUtils.setWord(null, false);
                            PlayerUtils.setBuilder(null);
                            PluginUtils.BMT.players.clear();
                        }
                    }
                } else if (args[0].equalsIgnoreCase("word")) {
                    if (!Rank.getRank(sender, Rank.Mod)) {
                        Rank.noPermissions(sender, Rank.Mod); //tell them this command is moderators and up only
                        return true; //and return out of the command
                    }
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.BLUE + "Usage: /bmt word <word>");
                    } else if (args.length >= 3) {
                        sender.sendMessage(ChatColor.BLUE + "Usage: /bmt word <word>");
                    } else {
                        ChatUtils.setWord(args[1].toLowerCase(), true); //set the word
                    }
                } else if (args[0].equalsIgnoreCase("round")) {
                    if (!Rank.getRank(sender, Rank.Mod)) {
                        Rank.noPermissions(sender, Rank.Mod); //tell them this command is moderators and up only
                        return true; //and return out of the command
                    }
                    if (!PluginUtils.BMT.enabled) {
                        sender.sendMessage(ChatColor.RED + "Build My Thing isn't even started!"); //tell them build my thing isn't even enabled
                    } else {
                        sender.sendMessage(ChatColor.BLUE + "Starting a new round!");
                        Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("ZeroCraft"), new NewRound()); //start a new round
                    }
                } else if (args[0].equalsIgnoreCase("info")) {
                    sender.sendMessage(ChatColor.BLUE + "Time left this round " + PluginUtils.chars[1] + ChatColor.GREEN + " " + ChatColor.ITALIC + MainTimer.am);
                    sender.sendMessage(ChatColor.BLUE + "Build My Thing running " + PluginUtils.chars[1] + ChatColor.GREEN + " " + ChatColor.ITALIC + PluginUtils.BMT.enabled);
                    sender.sendMessage(ChatColor.BLUE + "Current builder " + PluginUtils.chars[1] + ChatColor.GREEN + " " + ChatColor.ITALIC + (PlayerUtils.getBuilder() == null ? "null" : PlayerUtils.getBuilder().getName()));
                    sender.sendMessage(ChatColor.BLUE + "Current word " + PluginUtils.chars[1] + ChatColor.GREEN + " " + ChatColor.ITALIC + ((sender.getName().equals("TheNameMan") && PluginUtils.BMT.dev || sender.getName().equals("CommonSense_64")) ? ChatUtils.getWord() : "null"));
                    sender.sendMessage(ChatColor.BLUE + "Current round " + PluginUtils.chars[1] + ChatColor.GREEN + " " + ChatColor.ITALIC + PluginUtils.BMT.round);
                } else if (args[0].equals("dev")) {
                    if (sender.getName().equals("TheNameMan")) { //check if i678 or Common
                        if (PluginUtils.BMT.dev) {
                            PluginUtils.BMT.dev = false; //disable dev mode
                            StaffChat.notice(ChatColor.RED + "Disabled Build My Thing DEV mode.", sender.getName()); //and send a notice to staff saying so
                        } else {
                            PluginUtils.BMT.dev = true; //enable dev mode
                            StaffChat.notice(ChatColor.RED + "Enabled Build My Thing DEV mode.", sender.getName()); //and send a notice to staff saying so
                        }
                    } else {
                        Rank.noPermissions(sender, Rank.Owner); //show them a fake "owner rank required to run this command" message, when really it's only TheNameMan and Common who can use this
                    }
                } else {
                    sender.sendMessage(CommandUtils.formattedCmd("Build My Thing", true));
                    if (Rank.getRank(sender, Rank.Mod)) {
                        sender.sendMessage("/bmt game " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " Enabling the game");
                        sender.sendMessage("/bmt round " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " Start a new round");
                    }
                    sender.sendMessage("/bmt info " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.YELLOW + " Get current game info");
                    if (sender.getName().equals("TheNameMan")) {
                        sender.sendMessage("/bmt dev " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.LIGHT_PURPLE + " Enable/disable BMT dev mode");
                    }
                    sender.sendMessage(CommandUtils.formattedCmd("Build My Thing", true));
                }
            }
            return true;        }
    }

    /**
     * New round code for Build My Thing.
     */
    public static class NewRound implements Runnable {

        public void run() {
            //ChatUtils.logMessage(Level.INFO, "Starting a new round...");
            for (String p : PluginUtils.BMT.players) {
                if (Bukkit.getPlayerExact(p) == null)
                    continue; //if the player is not online, don't care about them and don't give their tokens as they logged off
                if (PluginUtils.BMT.tokens.containsKey(Bukkit.getPlayerExact(p).getUniqueId())) {
                    PluginUtils.BMT.tokens.put(Bukkit.getPlayerExact(p).getUniqueId(), PluginUtils.BMT.tokens.get(Bukkit.getPlayerExact(p).getUniqueId()) + 3); //give the player their correct tokens
                    Bukkit.getPlayerExact(p).sendMessage(ChatColor.BLUE + "You earned 3 tokens from this round!"); //tell them they got some tokens
                } else {
                    PluginUtils.BMT.tokens.put(Bukkit.getPlayerExact(p).getUniqueId(), 3); //set up their tokens count
                    Bukkit.getPlayerExact(p).sendMessage(ChatColor.BLUE + "You earned 3 tokens from this round!"); //tell them they got some tokens
                    Bukkit.getPlayerExact(p).sendMessage(ChatColor.RED + "The tokens system currently is temporary until I can get around to a proper storage system for the tokens. Your tokens are currently lost on a server reload or restart but I'm working on it! -i678"); //and tell them this points system is temporary.
                }
            }
            PluginUtils.BMT.players.clear();
            MainTimer.am = 120;
            if (PluginUtils.BMT.round + 1 >= 31) {
                //ChatUtils.logMessage(Level.INFO, "Round 30 is over, ending game.");
                PluginUtils.BMT.enabled = false;
                ChatUtils.broadcast(ChatColor.YELLOW + "[BMT] The Build My Thing minigame is now " + ChatColor.RED + "ENDED!" + ChatColor.GRAY + " The word was: " + ChatColor.LIGHT_PURPLE + ChatUtils.getWord()); //the round count is at or over 31, so end the game
                ChatUtils.setWord(null, false); //set the word to null and don't give the builder it
                PlayerUtils.getBuilder().setGameMode(GameMode.ADVENTURE); //set their gamemode to adventure first as it should not matter
                Location l = new Location(Bukkit.getWorld("arena"), 583.5, 8, 166.5); //initialize the teleport for players
                PlayerUtils.getBuilder().teleport(l); //teleport them to the area
                PlayerUtils.getBuilder().getInventory().clear(); //clear the builder's inventory
                PlayerUtils.getBuilder().playSound(PlayerUtils.getBuilder().getLocation(), Sound.CHEST_CLOSE, 1, 1); //and play a little sound of a chest closing because why not
                PlayerUtils.setBuilder(null); //set the builder to null
                return; //and return out of the statement.
            }
            if (PlayerUtils.getBuilder() != null) { //check if the builder is null just to be safe
                PlayerUtils.getBuilder().setGameMode(GameMode.ADVENTURE); //set the builders gamemode to adventure first as it should not matter
                Location l = new Location(Bukkit.getWorld("arena"), 583.5, 8, 166.5); //initialize the teleport for players
                PlayerUtils.getBuilder().teleport(l); //teleport the builder
                PlayerUtils.getBuilder().getInventory().clear(); //clear the builder's inventory
                PlayerUtils.getBuilder().playSound(PlayerUtils.getBuilder().getLocation(), Sound.CHEST_CLOSE, 1, 1); //and play a little sound of a chest closing because why not
            }
            ArrayList<Player> p = new ArrayList<>(); //set up the array list
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (SCType.getType(pl.getUniqueId()) == SCType.BMT) {
                    p.add(pl); //the player is in the BMT channel so add them to the array list
                }
            }
            if (p.size() == 0) {
                StaffChat.BMTNotify("There are no users in the Build My Thing channel for some reason. Not starting a new round."); //essentially ignore the new round if there are no users in the build my thing channel
                return;
            } else if (p.size() == 1) {
                PlayerUtils.setBuilder(p.get(0)); //set the builder to literally the only one in it [just for easier testing]
            } else {
                PlayerUtils.setBuilder(PluginUtils.BMT.randomBuilder(p)); //get a random builder from the player ArrayList<Player> containing all bmt players
            }
            PluginUtils.BMT.round++; //bump up the round count
            Location loc = new Location(Bukkit.getWorld("arena"), 583.5, 8, 166.5); //initialize the building area teleport
            PlayerUtils.getBuilder().teleport(loc); //teleport them to the building area
            PlayerUtils.getBuilder().setGameMode(GameMode.CREATIVE); //set their gamemode to creative after they're teleported
            ChatUtils.broadcast(ChatColor.BLUE + "Round " + ChatColor.RED + PluginUtils.BMT.round + ChatColor.BLUE + " started!"); //and announce the round is started
        }

    }

    /**
     * Main timer for Build My Thing.
     */
    public static class MainTimer implements Runnable {

        public static int am = 120;

        public void run() {
            if (PluginUtils.BMT.enabled) {
                if (PlayerUtils.getBuilder() != null) {
                    if (ChatUtils.getWord() != null) {
                        am = am - 1;
                        if (am == 60) {
                            ChatUtils.broadcast(ChatColor.DARK_GREEN + "1 minute left!");
                        } else if (am == 30) {
                            ChatUtils.broadcast(ChatColor.DARK_GREEN + "30 seconds left!");
                        } else if (am == 20) {
                            ChatUtils.broadcast(ChatColor.GREEN + "20 seconds left!");
                        } else if (am == 10) {
                            ChatUtils.broadcast(ChatColor.GOLD + "10 seconds left!");
                        } else if (am == 5) {
                            ChatUtils.broadcast(ChatColor.GOLD + "5 seconds left!");
                        } else if (am == 4) {
                            ChatUtils.broadcast(ChatColor.GOLD + "4 seconds left!");
                        } else if (am == 3) {
                            ChatUtils.broadcast(ChatColor.RED + "3 seconds left!");
                        } else if (am == 2) {
                            ChatUtils.broadcast(ChatColor.RED + "2 seconds left!");
                        } else if (am == 1) {
                            ChatUtils.broadcast(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 second left!");
                            ChatUtils.broadcast(ChatColor.DARK_RED + "" + ChatColor.BOLD + "1 second left!");
                        } else if (am == 0) {
                            ChatUtils.broadcast(ChatColor.DARK_RED + "Round over! The word this round was " + ChatColor.GRAY + PluginUtils.chars[1] + " " + ChatColor.RED + ChatUtils.word);
                            PlayerUtils.setBuilder(null);
                            ChatUtils.word = null;
                            ChatUtils.setWord(PluginUtils.BMT.randomWord(), true);
                        }
                    }
                }
            }
        }

    }
}
