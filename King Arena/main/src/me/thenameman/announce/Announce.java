//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.thenameman.announce;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kitteh.tag.TagAPI;

public class Announce extends JavaPlugin implements Listener {

    boolean damage = true;


    public Announce() {
    }

    public void onDisable() {
    }

    public void onEnable() {
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onEntityDamage(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if(e instanceof Player && !this.damage) {
            Player player = (Player)e;
            event.setDamage(0.0D);
            event.setCancelled(true);
            player.setHealth(20.0D);
        } else if(e instanceof Player && this.damage) {
            event.setCancelled(false);
        }

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cast = "";
        String[] var9 = args;
        int var8 = args.length;

        int targetSender;
        for(targetSender = 0; targetSender < var8; ++targetSender) {
            String player = var9[targetSender];
            cast = cast + player + " ";
        }

        Player var10 = (Player)sender;
        if(!label.equalsIgnoreCase("announce") && !label.equalsIgnoreCase("bc") && !label.equalsIgnoreCase("broadcast")) {
            if(label.equalsIgnoreCase("shout")) {
                if(args.length < 1) {
                    var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                    var10.sendMessage(ChatColor.RED + "Usage:" + ChatColor.RED + " " + ChatColor.GOLD + "/shout <message>");
                } else {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "[Shout]" + ChatColor.WHITE + " " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GREEN + ": " + cast);
                }
            } else if(!label.equalsIgnoreCase("cc") && !label.equalsIgnoreCase("clearchat")) {
                if(!label.equalsIgnoreCase("ccmy") && !label.equalsIgnoreCase("clearmychat")) {
                    if(label.equalsIgnoreCase("stream")) {
                        if(args.length < 1) {
                            var10.sendMessage(ChatColor.RED + "Incorrect number of arguments.");
                            var10.sendMessage(ChatColor.RED + "Usage:" + ChatColor.GOLD + " /stream <stream link>");
                        } else {
                            Bukkit.broadcastMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + var10.getName() + " is currently streaming at " + ChatColor.GOLD + ChatColor.UNDERLINE + cast);
                        }
                    } else if(label.equalsIgnoreCase("bar-announce")) {
                        if(args.length < 1) {
                            var10.sendMessage(ChatColor.RED + "Incorrect number of arguments.");
                            var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bar-announce <message>");
                        } else {
                            var10.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "This is a work in progress command. This will be added in later on.");
                        }
                    } else if(!label.equalsIgnoreCase("staffchat") && !label.equalsIgnoreCase("sc")) {
                        if(label.equalsIgnoreCase("rules")) {
                            if(args.length == 0) {
                                var10.sendMessage(ChatColor.RED + "1. " + ChatColor.BLUE + "No intentional griefing. (Griefing to get into a base during a raid is okay.)");
                                var10.sendMessage(ChatColor.RED + "2. " + ChatColor.BLUE + "No asking for ranks or OP.");
                                var10.sendMessage(ChatColor.RED + "3. " + ChatColor.BLUE + "Don\'t bug staff while they are working.");
                                var10.sendMessage(ChatColor.RED + "4. " + ChatColor.BLUE + "Do not hack.");
                                var10.sendMessage(ChatColor.RED + "5. " + ChatColor.BLUE + "Use common sense.");
                                var10.sendMessage(ChatColor.RED + "6. " + ChatColor.BLUE + "Do not ask for creative mode, items, etc.");
                                var10.sendMessage(ChatColor.RED + "7. " + ChatColor.BLUE + "Do not spam.");
                                var10.sendMessage(ChatColor.RED + "8. " + ChatColor.BLUE + "If promoted, you are expected not to abuse your powers.");
                                var10.sendMessage(ChatColor.RED + "9. " + ChatColor.BLUE + "Do not be racist or sexist.");
                            } else {
                                var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/rules");
                            }
                        } else if(label.equalsIgnoreCase("say")) {
                            sender.sendMessage(ChatColor.RED + "Incorrect command.");
                            sender.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "Commands:");
                            sender.sendMessage(ChatColor.BLUE + "Moderator: " + ChatColor.GOLD + "/msay <message>");
                            sender.sendMessage(ChatColor.BLUE + "Administrator: " + ChatColor.GOLD + "/asay <message>");
                            sender.sendMessage(ChatColor.BLUE + "CONSOLE: " + ChatColor.GOLD + "/csay <message>");
                        } else if(label.equalsIgnoreCase("csay")) {
                            if(sender != var10) {
                                Bukkit.broadcastMessage(ChatColor.RED + "[CONSOLE] " + ChatColor.BLUE + ChatColor.translateAlternateColorCodes('&', cast));
                            } else if(sender == var10 && var10.hasPermission("kingarena.console.say")) {
                                var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "This command is meant for the CONSOLE.");
                            } else if(sender == var10 && !var10.hasPermission("kingarena.console.say")) {
                                var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "This command is meant for the CONSOLE. You don\'t have the permission to run this command, anyway.");
                            }
                        } else if(label.equalsIgnoreCase("asay")) {
                            if(args.length < 1) {
                                var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/asay <message>");
                            } else {
                                for(targetSender = 0; targetSender < 3; ++targetSender) {
                                    Bukkit.broadcastMessage(" ");
                                }

                                Bukkit.broadcastMessage(ChatColor.RED + "[ADMIN] " + ChatColor.YELLOW + var10.getDisplayName() + ": " + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', cast));

                                for(targetSender = 0; targetSender < 3; ++targetSender) {
                                    Bukkit.broadcastMessage(" ");
                                }
                            }
                        } else if(label.equalsIgnoreCase("msay")) {
                            if(args.length == 0) {
                                var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/msay <message>");
                            } else {
                                Bukkit.broadcastMessage(ChatColor.BLUE + "[MOD] " + ChatColor.YELLOW + var10.getDisplayName() + ": " + ChatColor.GREEN + cast);
                            }
                        } else if(label.equalsIgnoreCase("potato")) {
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[King Arena]" + ChatColor.BLUE + " POTATOES FOR EVERYONE!!!");
                        } else {
                            Player var11;
                            if(label.equalsIgnoreCase("heal")) {
                                if(args.length == 0) {
                                    var10.setHealth(20.0D);
                                    var10.setFireTicks(0);
                                    var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "The gods have healed you with their magic.");
                                } else if(args.length == 1) {
                                    if(var10.getServer().getPlayer(args[0]) != null) {
                                        var11 = var10.getServer().getPlayer(args[0]);
                                        var11.setHealth(20.0D);
                                        var11.setFireTicks(0);
                                        var11.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GOLD + " has healed you!");
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Healed player " + args[0] + ChatColor.GOLD + ".");
                                    } else {
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Player \'" + args[0] + "\' is not online.");
                                    }
                                } else if(args.length > 1) {
                                    var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                    var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/heal [player]");
                                }
                            } else if(label.equalsIgnoreCase("kill")) {
                                if(args.length == 0) {
                                    var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                    var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/kill <player>");
                                } else if(args.length == 1) {
                                    if(var10.getServer().getPlayer(args[0]) != null) {
                                        var11 = var10.getServer().getPlayer(args[0]);
                                        var10.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 5000, 4));
                                        var11.setFireTicks(0);
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Killed player " + var11.getDisplayName() + ChatColor.RED + ".");
                                    } else {
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Player \'" + args[0] + "\' is not online.");
                                    }
                                } else if(args.length > 1) {
                                    var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                    var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/kill <player>");
                                }
                            } else if(label.equalsIgnoreCase("feed")) {
                                if(args.length == 0) {
                                    var10.setFoodLevel(20);
                                    var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "You have been fed.");
                                } else if(args.length == 1) {
                                    if(var10.getServer().getPlayer(args[0]) != null) {
                                        var11 = var10.getServer().getPlayer(args[0]);
                                        var11.setFoodLevel(20);
                                        var11.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GOLD + " has fed you!");
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Fed player " + var11.getDisplayName() + ChatColor.RED + ".");
                                    } else {
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Player \'" + args[0] + "\' is not online.");
                                    }
                                } else if(args.length > 1) {
                                    var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                    var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/feed [player]");
                                }
                            } else if(label.equalsIgnoreCase("repair")) {
                                if(args.length == 0) {
                                    var10.getInventory().getItemInHand().setDurability((short)0);
                                    var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Repaired the tool in your hand.");
                                } else if(args.length == 1) {
                                    if(var10.getServer().getPlayer(args[0]) != null) {
                                        var11 = var10.getServer().getPlayer(args[0]);
                                        var11.getInventory().getItemInHand().setDurability((short)0);
                                        var11.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GOLD + " has repaired the item you have in your hand!");
                                    } else {
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.RED + "Player \'" + args[0] + "\' is not online.");
                                    }
                                }
                            } else if(!label.equalsIgnoreCase("durability") && !label.equalsIgnoreCase("dura")) {
                                if(label.equalsIgnoreCase("test-break")) {
                                    var10.getInventory().getItemInHand().setDurability((short)2000);
                                    this.getLogger().info("Someone used the TEST BREAK command.");
                                    if(var10.getInventory().getItemInHand().getDurability() == 0) {
                                        this.getLogger().info("The player who ran this command obviously didn\'t do it on a damagable item.");
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "This command must be ran on a DAMAGABLE item, for example a DIAMOND_SWORD.");
                                    }
                                } else if(label.equalsIgnoreCase("damage")) {
                                    if(this.damage) {
                                        this.damage = false;
                                        Bukkit.broadcastMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GRAY + " has disabled player damage.");
                                    } else if(!this.damage) {
                                        this.damage = true;
                                        Bukkit.broadcastMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + var10.getDisplayName() + ChatColor.GRAY + " has enabled player damage.");
                                    }
                                } else if(label.equalsIgnoreCase("refresh-me")) {
                                    TagAPI.refreshPlayer(var10);
                                    var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GREEN + "Your nametag has been forcefully refreshed! " + ChatColor.RED + "Notice: This is done automatically when you change your tag. You only need to use this when it bugs out.");
                                } else if(label.equalsIgnoreCase("troll")) {
                                    if(args.length == 0) {
                                        var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GREEN + "Trolled yourself! :D");
                                        var10.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10000, 1));
                                        var10.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10000, 1));
                                        var10.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000, 100));
                                    } else if(args.length == 1) {
                                        if(var10.getServer().getPlayer(args[0]) != null) {
                                            var11 = var10.getServer().getPlayer(args[0]);
                                            var11.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10000, 1));
                                            var11.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10000, 1));
                                            var11.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000, 100));
                                        }
                                    } else if(args.length > 1) {
                                        var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
                                        var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/troll [player]");
                                    }
                                } else if(label.equalsIgnoreCase("fw")) {
                                    this.shootFireworks();
                                }
                            } else if(var10.getInventory().getItemInHand().getDurability() == -1) {
                                var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Hold an actual damagable-item to be told it\'s durability. If the durability output was put here, it would be -1, which is essentially infinite.");
                            } else if(var10.getInventory().getItemInHand().getDurability() == 0) {
                                var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "This item is fully repaired, or is an item that does not take durability damage.");
                            } else if(var10.getInventory().getItemInHand().getDurability() > 0) {
                                var10.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "The technical durability for the item in your hand is: " + ChatColor.RED + var10.getInventory().getItemInHand().getDurability());
                            }
                        }
                    } else if(args.length < 1) {
                        var10.sendMessage(ChatColor.RED + "Incorrect number of arguments.");
                        var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/sc <your message>");
                    } else {
                        Bukkit.broadcast(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Staff Chat" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + var10.getDisplayName() + ": " + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', cast), "announcer.staffchat");
                    }
                } else {
                    for(targetSender = 0; targetSender < 120; ++targetSender) {
                        var10.sendMessage(" ");
                    }

                    var10.sendMessage(ChatColor.RED + "[King Arena]" + ChatColor.GRAY + " " + "You cleared your own chat.");
                }
            } else {
                for(targetSender = 0; targetSender < 120; ++targetSender) {
                    Bukkit.broadcastMessage(" ");
                }

                Bukkit.broadcastMessage(ChatColor.RED + "[King Arena] " + ChatColor.YELLOW + var10.getName() + ChatColor.GRAY + " " + ChatColor.DARK_GRAY + "has just cleared the chat.");
                var10.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "[Warning] " + ChatColor.GREEN + "You have cleared global chat. Now would be a good time to leave if you shouldn\'t have...");
            }
        } else if(args.length < 1) {
            var10.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
            var10.sendMessage(ChatColor.RED + "Usage: " + ChatColor.RED + ChatColor.GOLD + "/announce <message>");
        } else {
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.GOLD + "Broadcast" + ChatColor.DARK_RED + "] " + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', cast));
        }

        return false;
    }

    private void shootFireworks() {
        Player[] var4;
        int var3 = (var4 = Bukkit.getOnlinePlayers()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            Player player = var4[var2];
            Firework fw = (Firework)player.getWorld().spawn(player.getLocation(), Firework.class);
            FireworkMeta fm = fw.getFireworkMeta();
            Random r = new Random();
            int fType = r.nextInt(5) + 1;
            Type type = null;
            switch(fType) {
                case 1:
                default:
                    type = Type.BALL;
                    break;
                case 2:
                    type = Type.STAR;
                    break;
                case 3:
                    type = Type.BALL_LARGE;
                    break;
                case 4:
                    type = Type.CREEPER;
                    break;
                case 5:
                    type = Type.BURST;
            }

            int c1i = r.nextInt(17) + 1;
            int c2i = r.nextInt(17) + 1;
            Color c1 = this.getColour(c1i);
            Color c2 = this.getColour(c2i);
            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
            fm.addEffect(effect);
            int power = r.nextInt(2) + 1;
            fm.setPower(power);
            fw.setFireworkMeta(fm);
        }

    }

    public Color getColour(int c) {
        switch(c) {
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.WHITE;
            case 3:
                return Color.TEAL;
            case 4:
                return Color.SILVER;
            case 5:
                return Color.RED;
            case 6:
                return Color.PURPLE;
            case 7:
                return Color.ORANGE;
            case 8:
                return Color.OLIVE;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.MAROON;
            case 11:
                return Color.LIME;
            case 12:
                return Color.GREEN;
            case 13:
                return Color.GRAY;
            case 14:
                return Color.FUCHSIA;
            case 15:
                return Color.BLUE;
            case 16:
                return Color.BLACK;
            case 17:
                return Color.AQUA;
            default:
                return null;
        }
    }
}
