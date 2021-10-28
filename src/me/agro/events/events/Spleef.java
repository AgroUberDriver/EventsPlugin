package me.agro.events.events;

import me.agro.events.Main;
import me.agro.events.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spleef {


    public static void StartSpleef() {
        Main plugin = Main.getInstance();
        String prefix = plugin.getConfig().getString("MESSAGES.PREFIX");

        new BukkitRunnable() {
            @Override
            public void run() {

                String raw12 = plugin.getConfig().getString("EVENT-OPEN-COUNTDOWN").replaceAll("%event%", "Spleef");
                String raw11 = raw12.replaceAll("%time%", "30 seconds");

                for (Player player : plugin.eventPlayers) {

                    player.sendMessage(Chat.color(prefix + raw11));

                }
            }
        }.runTaskLater(plugin, 20 * 0);

        new BukkitRunnable() {
            @Override
            public void run() {

                String raw12 = plugin.getConfig().getString("EVENT-OPEN-COUNTDOWN").replaceAll("%event%", "Spleef");
                String raw11 = raw12.replaceAll("%time%", "15 seconds");

                for (Player player : plugin.eventPlayers) {

                    player.sendMessage(Chat.color(prefix + raw11));

                }
            }
        }.runTaskLater(plugin, 20 * 15);

        new BukkitRunnable() {
            @Override
            public void run() {

                String raw12 = plugin.getConfig().getString("EVENT-OPEN-COUNTDOWN").replaceAll("%event%", "Spleef");
                String raw11 = raw12.replaceAll("%time%", "5 seconds");

                for (Player player : plugin.eventPlayers) {

                    player.sendMessage(Chat.color(prefix + raw11));

                }
            }
        }.runTaskLater(plugin, 20 * 25);
    }
}
