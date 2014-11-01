package me.zerocraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

/**
 * Command utilities.
 */
public class CommandUtils {

    public static String formatCast(String[] args) {
        String cast = "";
        for(String s : args) {
            if(s.equals("") && args.length == 1) break;
            else if(s.equals("")) continue;
            cast = cast + s + " ";
        }
        return cast;
    }

    public static String formattedCmd(String name, boolean header) {
        return (header ? ChatColor.LIGHT_PURPLE + "-•- [" + ChatColor.LIGHT_PURPLE + ChatColor.stripColor(name) + ChatColor.LIGHT_PURPLE + "] -•-" : ChatColor.BLUE + "-•- " + ChatColor.stripColor(name) + ChatColor.BLUE + " -•-");
    }

    public static void shootFireworks(CommandSender sender) {
        Player p = (Player) sender;
        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        Random r = new Random();
        FireworkEffect.Type type = getFireworkType();
        int c1i = r.nextInt(17) + 1;
        int c2i = r.nextInt(17) + 1;
        Color c1 = getColour(c1i);
        Color c2 = getColour(c2i);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fm.addEffect(effect);
        int power = r.nextInt(3) + 1;
        fm.setPower(power);
        fw.setFireworkMeta(fm);
    }

    private static FireworkEffect.Type getFireworkType() {
        Random r = new Random();
        FireworkEffect.Type type;
        int fType = r.nextInt(5) + 1;
        switch (fType) {
            default:
            case 1:
                type = FireworkEffect.Type.BALL;
                break;
            case 2:
                type = FireworkEffect.Type.STAR;
                break;
            case 3:
                type = FireworkEffect.Type.BALL_LARGE;
                break;
            case 4:
                type = FireworkEffect.Type.CREEPER;
                break;
            case 5:
                type = FireworkEffect.Type.BURST;
        }
        return type;
    }

    private static Color getColour(int c) {
        switch (c) {
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
        }
        return null;
    }

}