package me.zerocraft.commands.user;

import me.zerocraft.utils.CommandUtils;
import me.zerocraft.utils.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Command: /tutorial<br>
 * Rank: MEMBER
 */
public class CommandTutorial implements CommandExecutor {

    char[] chars = PluginUtils.chars;

    public boolean onCommand(CommandSender sender, Command label, String c, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(CommandUtils.formattedCmd("Tutorials", true));
            sender.sendMessage("/tutorial basics " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " The basics");
            sender.sendMessage("/tutorial claiming <GP|LWC> " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Claiming tutorials");
            sender.sendMessage("/tutorial faq " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Frequently asked questions");
            sender.sendMessage("/tutorial ranks " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Server ranks");
            sender.sendMessage("/tutorial forums " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Server forums");
            sender.sendMessage("/tutorial teamspeak " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " TeamSpeak server info");
            sender.sendMessage("/tutorial plugdj " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " PlugDJ tutorial");
            sender.sendMessage(CommandUtils.formattedCmd("Tutorials", true));
        } else {
            if(args[0].equalsIgnoreCase("claiming")) {
                if(args.length == 1) {
                    String[] pages = {ChatColor.LIGHT_PURPLE + "-•- [Claiming] -•-", "/tutorial claiming LWC » LWC claiming tutorial", "/tutorial claiming GP » GriefPrevention claiming tutorial", ChatColor.LIGHT_PURPLE + "-•- [Claiming] -•-"};
                    sender.sendMessage(pages);
                } else {
                    if(args[1].equalsIgnoreCase("gp") || args[1].equalsIgnoreCase("griefprevention")) {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [GriefPrevention] -•-");
                        sender.sendMessage("To get started with claiming, you requre a Golden Shovel. Once you have that, right click the two corners of your claim area. The area MUST be a minimum of " + ChatColor.RED + "10x10" + ChatColor.WHITE + ". You get more claim blocks by actively playing. To delete a claim you no longer want, do /abandonclaim. If there is any water that is not at or under sea level (64), it will ask for a confirmation if you want to continue.");
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [GriefPrevention] -•-");
                    } else if(args[1].equalsIgnoreCase("lwc")) {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [LWC] -•-");
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.RED + "Disclaimer: LWC only works on chests, droppers, signs, and those sorts of stuff. This will NOT work to claim things like cobblestone. If you want to claim things like that, look at the GriefPrevention claiming tutorial.");
                        sender.sendMessage("To claim with LWC, the main command is /lock. When you place a chest, it is automatically locked, thus preventing other people from going through your stuff without your permission. To allow people to use your chests, do /cmodify [@]<user>. If you add an '@' symbol before their name, they are considered an admin on that protection, which means they can add other users to the protection, but not as an admin. They cannot remove you from the protection. If you add a '-' symbol in front of their name, that removes them from the protection. Simple. To unclaim a protection, do /unlock.");
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [LWC] -•-");
                    }
                }
            } else if(args[0].equalsIgnoreCase("basics")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Basics] -•-");
                sender.sendMessage(" ");
                sender.sendMessage("Claiming: " + ChatColor.BLUE + "[/tutorial claiming]");
                sender.sendMessage("Welcome to ZeroCraft! This server is home to the streamer MisterZeroDegrees. To get started with Survival, walk through the SURVIVAL portal in the Portals room, straight from where you spawn. For creative, go in the CREATIVE portal." + ChatColor.GREEN + " Remember: Punch tree, get wood.");
                sender.sendMessage("For the most commonly asked questions, do [/tutorials FAQ.]");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Basics] -•-");
            } else if(args[0].equalsIgnoreCase("teamspeak") || args[0].equalsIgnoreCase("ts")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [TeamSpeak] -•-");
                sender.sendMessage(" ");
                sender.sendMessage("You can download TeamSpeak from here." + ChatColor.BLUE + " http://www.teamspeak.com/?page=downloads " + ChatColor.RESET + "(Make sure you download TeamSpeak 3!)");
                sender.sendMessage("In order to join the TeamSpeak server, use these details.");
                sender.sendMessage("IP: 198.245.62.91:9006");
                sender.sendMessage("(For in case you don't know how to join, follow these steps of instructions: Connections > Connect > put those details in > Connect. Enjoy!)");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [TeamSpeak] -•-");
            } else if(args[0].equalsIgnoreCase("faq") || args[0].equalsIgnoreCase("frequentlyaskedquestions")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [FAQ] -•-");
                sender.sendMessage(" ");
                sender.sendMessage("Q: How do I claim my house?");
                sender.sendMessage("A: Do" + ChatColor.BLUE + " [/tutorial claiming <GP|LWC>] " + ChatColor.RESET + "for a basic overview on claiming.");
                sender.sendMessage(" ");
                sender.sendMessage("Q: How do I get mod?");
                sender.sendMessage("A: By not asking for it, being on the server alot and you will be messaged if we need more in the future.");
                sender.sendMessage(" ");
                sender.sendMessage("Q: What is THEE07's Twitch stream?");
                sender.sendMessage("A: www.twitch.tv/MisterZeroDegrees");
                sender.sendMessage(" ");
                sender.sendMessage("Q: How do I join the TeamSpeak server?");
                sender.sendMessage("A: Do " + ChatColor.BLUE + "[/tutorial teamspeak] " + ChatColor.WHITE + "for a basic overview on how to join it.");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [FAQ] -•-");
            } else if(args[0].equals("forums")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Forums] -•-");
                sender.sendMessage(" ");
                sender.sendMessage("We now have forums! You can view them HERE: " + ChatColor.BLUE + " http://zerocraft-mc.enjin.com/forum " + ChatColor.WHITE + "");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Forums] -•-");
            } else if(args[0].equalsIgnoreCase("ranks")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Ranks] -•-");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.BLUE + "Owners:");
                sender.sendMessage("Owners are the head of the server. They can make all decisions.");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.RED + "Administrators:");
                sender.sendMessage("Administrators keep the server clear of bad people. Simple as that. Pretty much a higher up version of a Moderator.");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.RED + "Devs:");
                sender.sendMessage("Developers for the server. They make the plugin and keep it running smoothly with updates. Found a bug? Report it to these people!");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.DARK_GREEN + "Moderators:");
                sender.sendMessage("Moderators watch the server chat and can also do.. well moderator stuff. Need help? Ask these people!");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.YELLOW + "Members:");
                sender.sendMessage("Normal users of the server.");
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [Ranks] -•-");
            } else if(args[0].equalsIgnoreCase("plugdj")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [PlugDJ] -•-");
                sender.sendMessage("We have a PlugDJ you can join! The link is "
                        + ChatColor.BLUE + "http://plug.dj/misterzerodegrees/ "
                        + ChatColor.WHITE
                        + "- if Zero is streaming at the time and is on PlugDJ, please, keep to the Twitch chat! If you don't know how to use PlugDJ, look at this tutorial made by Pillow_Ninja:"
                        + ChatColor.BLUE
                        + " https://www.youtube.com/watch?v=iu3JRzFVbqk");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "-•- [PlugDJ] -•-");
            } else {
                sender.sendMessage(CommandUtils.formattedCmd("Tutorials", true));
                sender.sendMessage("/tutorial basics " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " The basics");
                sender.sendMessage("/tutorial claiming <GP|LWC> " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Claiming tutorials");
                sender.sendMessage("/tutorial faq " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Frequently asked questions");
                sender.sendMessage("/tutorial ranks " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Server ranks");
                sender.sendMessage("/tutorial forums " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " Server forums");
                sender.sendMessage("/tutorial teamspeak " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " TeamSpeak server info");
                sender.sendMessage("/tutorial plugdj " + ChatColor.GRAY + chars[1] + ChatColor.LIGHT_PURPLE + " PlugDJ tutorial");
                sender.sendMessage(CommandUtils.formattedCmd("Tutorials", true));
            }
        }
        return true;
    }
}
