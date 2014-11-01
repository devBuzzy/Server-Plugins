//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.thenameman.compasswarp;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CompassWarp extends JavaPlugin implements Listener {

    public CompassWarp() {
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getLogger().info("If there is a flood of warnings from this plugin in the console from people clicking off the item menu, #BlameDinnerbone");
    }

    private void teleportInWorld(Player player, int x, int y, int z) {
        player.teleport(new Location(player.getWorld(), (double)x, (double)y, (double)z));
    }

    private void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.DARK_GREEN + "Warper");
        ItemStack survival = new ItemStack(Material.STONE_SWORD);
        ItemMeta survivalMeta = survival.getItemMeta();
        ItemStack creative = new ItemStack(Material.GRASS);
        ItemMeta creativeMeta = creative.getItemMeta();
        ItemStack minigames = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta minigamesMeta = minigames.getItemMeta();
        ItemStack hub = new ItemStack(Material.WORKBENCH);
        ItemMeta hubMeta = hub.getItemMeta();
        survivalMeta.setDisplayName(ChatColor.BLUE + "Survival");
        survival.setItemMeta(survivalMeta);
        creativeMeta.setDisplayName(ChatColor.GREEN + "Creative");
        creative.setItemMeta(creativeMeta);
        minigamesMeta.setDisplayName(ChatColor.GOLD + "Minigames Hub");
        minigames.setItemMeta(minigamesMeta);
        hubMeta.setDisplayName(ChatColor.DARK_BLUE + "Hub");
        hub.setItemMeta(hubMeta);
        inv.setItem(3, survival);
        inv.setItem(4, minigames);
        inv.setItem(5, creative);
        inv.setItem(22, hub);
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Warper")) {
            Player player = (Player)event;
            if(!player.hasPermission("warper.use")) {
                player.sendMessage(ChatColor.RED + "You can only use this in the HUB world.");
            } else {
                Player p = (Player)event.getWhoClicked();
                event.setCancelled(true);
                switch($SWITCH_TABLE$org$bukkit$Material()[event.getCurrentItem().getType().ordinal()]) {
                    case 3:
                        this.teleportInWorld(player, 0, 100, 20);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Warped to " + ChatColor.GREEN + "CREATIVE portal");
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Note: The reason why you were moved to the portal is because of a current limitation in the WARPER plugin. -TheNameMan, Dev");
                        break;
                    case 59:
                        this.teleportInWorld(player, 0, 100, 0);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Warped to the " + ChatColor.GREEN + "HUB");
                        break;
                    case 189:
                        this.teleportInWorld(player, 0, 100, 0);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Warped to " + ChatColor.GREEN + "SURVIVAL portal");
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Note: The reason why you were moved to the portal is because of a current limitation in the WARPER plugin. -TheNameMan, Dev");
                        break;
                    case 294:
                        this.teleportInWorld(player, 20, 100, 20);
                        p.closeInventory();
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Warped to " + ChatColor.GREEN + "MINIGAMES HUB portal");
                        p.sendMessage(ChatColor.BLUE + "[King Arena] " + ChatColor.GOLD + "Note: The reason why you were moved to the portal is because of a current limitation in the WARPER plugin. -TheNameMan, Dev");
                }

            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("warper.use")) {
            ItemStack compass = new ItemStack(Material.WATCH);
            ItemMeta compassMeta = compass.getItemMeta();
            compassMeta.setDisplayName(ChatColor.GOLD + "Compass Warper" + ChatColor.BLUE + " - " + ChatColor.AQUA + "Right Click Me!");
            compass.setItemMeta(compassMeta);
            player.getInventory().clear();
            player.getInventory().addItem(new ItemStack[]{compass});
        } else {
            this.getLogger().info("Debug: Someone joined without the WARPER.USE permission. Most likely joined in a Non-Hub world, or their WARPER.USE permission was removed (negated). Nothing to be worried about.");
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();
        if(a != Action.PHYSICAL && is != null && is.getType() != Material.AIR) {
            if(is.getType() == Material.WATCH) {
                Player player = (Player)event;
                if(!player.hasPermission("warper.use")) {
                    player.sendMessage(ChatColor.RED + "You can only use this in the HUB world.");
                } else {
                    this.openGUI(event.getPlayer());
                }
            }

        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if(label.equalsIgnoreCase("warper")) {
            if(!player.hasPermission("warper.use")) {
                player.sendMessage(ChatColor.RED + "You can only use this in the HUB world.");
            } else {
                this.openGUI(player);
            }
        }

        return true;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Iterator var3 = event.blockList().iterator();

        while(var3.hasNext()) {
            Block b = (Block)var3.next();
            float x = -2.0F + (float)(Math.random() * 5.0D);
            float y = -2.0F + (float)(Math.random() * 5.0D);
            float z = -2.0F + (float)(Math.random() * 5.0D);
            FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
            fallingBlock.setDropItem(false);
            fallingBlock.setVelocity(new Vector(x, y, z));
            b.setType(Material.AIR);
        }

    }
}
