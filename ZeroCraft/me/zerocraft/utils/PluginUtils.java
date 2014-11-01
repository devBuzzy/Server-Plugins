package me.zerocraft.utils;

import me.zerocraft.staffchat.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

import java.util.*;

/**
 * Various plugin utilities.
 */
public class PluginUtils {

    public static char[] chars = {
            '>',
            '»',
            '♦',
            '•',
            '■',
            '™',
            '↔',
            '‼',
            '♥',
            '↨',
            '►',
            '→'
    };
    public static String msgNormal = ChatColor.GREEN + "ZeroCraft » " + ChatColor.BLUE;
    public static boolean globalMute = false;
    public static Plugin pl = Bukkit.getPluginManager().getPlugin("ZeroCraft");
    public static ArrayList<String> gmuteBypass = new ArrayList<>();
    public static ArrayList<UUID> frozenPlayers = new ArrayList<>();
    public static ArrayList<String> reportedPlayers = new ArrayList<>();

    public static class BMT {

        public static boolean enabled = false;
        public static int round = 0;
        public static boolean dev = false;
        public static ArrayList<String> players = new ArrayList<>();
        public static HashMap<UUID, Integer> tokens = new HashMap<>();
        private static List<String> words = new ArrayList<>();
        private static Random r = new Random();

        //private static int word = 0;
        static int dummyWordInt = 30;
        private static List<String> previousWords = new ArrayList<>();

        public static void init() {
            setupWords();
            //LogUtils.info("[BMT] Build My Thing initialized! The amount of words in the randomizer is: " + words.size());
            if(dev) {
                LogUtils.info("Expect some spam of all the words in the randomizer... now. [Notice: This is only a dev thing. If this is still on from after dev mode, nag me about it.]");
                for(int i = 0; i < words.size(); i++) {
                    LogUtils.info("[#" + i + "] Word: " + words.get(i));
                }
            }
        }

        private static void setupWords() {
            words.add("boat");
            words.add("string");
            words.add("robot");
            words.add("cow");
            words.add("pig");
            words.add("potion");
            words.add("steve");
            words.add("block");
            words.add("bow");
            words.add("circle");
            words.add("grass");
            words.add("torch");
            words.add("water");
            words.add("lava");
            words.add("skeleton");
            words.add("zombie");
            words.add("chest");
            words.add("paper");
            words.add("batman");
            words.add("newspaper");
            words.add("door");
            words.add("freezer");
            words.add("skin");
            words.add("mojang");
            words.add("notch");
            words.add("workbench");
            words.add("stone");
            words.add("window");
            words.add("flower");
            words.add("misterzerodegrees");
        }

        public static String randomWord() {
            int getWord = r.nextInt(30) + 1;
            String word = words.get(getWord);
            if(wordWasUsed(word)) {
                do {
                    int intword = r.nextInt(30) + 1;
                    word = words.get(intword);
                } while(wordWasUsed(word));
            }
            previousWords.add(word);
            return word;
        }

        private static boolean wordWasUsed(String word) {
            return previousWords.contains(word);
        }

        public static String getWord(Player p, boolean devMode) {
            if(p.getName().equalsIgnoreCase("TheNameMan") && dev) {
                return ChatUtils.getWord();
            } else {
                return "null";
            }
        }

        public static Player randomBuilder(ArrayList<Player> player) {
            if(PluginUtils.pl.getConfig().getInt("buildmything.maxRandomPlayers") > 25) {
                StaffChat.notice(ChatColor.RED + "Max players in the configuration file is more than 25 players. FIX IT!", "Build My Thing");
                return null;
            }
            int list = 0;
            for(@SuppressWarnings("unused") Player pl : player) {
                if (list >= PluginUtils.pl.getConfig().getInt("buildmything.maxRandomPlayers")) {
                    break;
                }
                list++;
            }
            int fType = r.nextInt(list) + 1;
            switch (fType) {
                default:
                case 1:
                    return player.get(1);
                case 2:
                    return player.get(2);
                case 3:
                    return player.get(3);
                case 4:
                    return player.get(4);
                case 5:
                    return player.get(5);
                case 6:
                    return player.get(6);
                case 7:
                    return player.get(7);
                case 8:
                    return player.get(8);
                case 9:
                    return player.get(9);
                case 10:
                    return player.get(10);
                case 11:
                    return player.get(11);
                case 12:
                    return player.get(12);
                case 13:
                    return player.get(13);
                case 14:
                    return player.get(14);
                case 15:
                    return player.get(15);
                case 16:
                    return player.get(16);
                case 17:
                    return player.get(17);
                case 18:
                    return player.get(18);
                case 19:
                    return player.get(19);
                case 20:
                    return player.get(20);
                case 21:
                    return player.get(21);
                case 22:
                    return player.get(22);
                case 23:
                    return player.get(23);
                case 24:
                    return player.get(24);
                case 25:
                    return player.get(25);
            }
        }

    }

}