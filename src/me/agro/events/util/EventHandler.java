package me.agro.events.util;

import me.agro.events.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.agro.events.util.SchematicHandler.loadSchematic;

public class EventHandler {





    public static void StartEvent(String event) {

        Main plugin = Main.getInstance();
        String prefix = plugin.getConfig().getString("MESSAGES.PREFIX");

        if(plugin.activeEvent == false) {

            plugin.activeEvent = true;
            plugin.currentEvent = event;

            String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
            String raw11 = raw12.replaceAll("%time%", "1 minute");

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
            loadSchematic(event);
            new BukkitRunnable() {
                @Override
                public void run() {

                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "30 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));

                }
            }.runTaskLater(plugin, 20 * 30);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "10 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 50);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "5 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 55);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "4 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 56);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "3 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 57);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "2 seconds");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 58);

            new BukkitRunnable() {
                @Override
                public void run() {
                    String raw12 = plugin.getConfig().getString("MESSAGES.EVENT-COUNTDOWN").replaceAll("%event%", event);
                    String raw11 = raw12.replaceAll("%time%", "1 second");

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw11));
                }
            }.runTaskLater(plugin, 20 * 59);

            new BukkitRunnable() {
                @Override
                public void run() {
                    plugin.eventJoinable = true;
                    String raw = plugin.getConfig().getString("MESSAGES.EVENT-OPEN").replaceAll("%event%", event);

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw));


                    if(event.equalsIgnoreCase("LMS")) {
                        startLMS();
                    }
                }
            }.runTaskLater(plugin, 20 * 60);

        }
    }

    public static void playerDied(Player player) {
        Main plugin = Main.getInstance();
        for(Player p : plugin.eventPlayers) {
            plugin.eventPlayers.remove(player);
            String raw1 = plugin.getConfig().getString("MESSAGES.PLAYER-ELIMINATED").replaceAll("%player%", player.getDisplayName());
            String raw2 = raw1.replaceAll("%participants%", String.valueOf(plugin.eventPlayers.size()));
            player.sendMessage("Died");
            p.sendMessage(Chat.color(raw2));
        }
    }

    public static void startLMS() {
        Main plugin = Main.getInstance();

        new BukkitRunnable() {
            @Override
            public void run() {
                startLMSVote();
            }
        }.runTaskLater(plugin, 20 * 5);
    }

    public static void startLMSVote() {
        Main plugin = Main.getInstance();

        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&a&lKit Vote"));

        ItemStack Pot = new ItemStack(Material.POTION);

        PotionMeta potmeta = (PotionMeta) Pot.getItemMeta();
        potmeta.setMainEffect(PotionEffectType.HEAL);
        potmeta.setDisplayName(Chat.color("&a&lPotion Kit"));

        Pot.setItemMeta(potmeta);

        ItemStack ironpvp = new ItemStack(Material.IRON_AXE);

        ItemMeta ironmeta = ironpvp.getItemMeta();
        ironmeta.setDisplayName(Chat.color("&a&lAxe Kit"));
        ironpvp.setItemMeta(ironmeta);

        inv.setItem(3, Pot);

        inv.setItem(6, ironpvp);


        for(Player p : plugin.eventPlayers) {
            p.openInventory(inv);
        }

    }


}
