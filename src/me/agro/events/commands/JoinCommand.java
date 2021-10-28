package me.agro.events.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import me.agro.events.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;

import static me.agro.events.util.InventoryHandler.saveInventory;

public class JoinCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    String prefix = plugin.getConfig().getString("MESSAGES.PREFIX");


    @Override
    public void onCommand(Player player, String[] args) throws IOException {
            if (plugin.eventJoinable == true) {
                if(plugin.eventPlayers.contains(player)) {
                  player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are already in an event! Type /event leave to leave."));
                } else {
                    plugin.eventPlayers.add(player);
                    String raw1 = plugin.getConfig().getString("MESSAGES.JOIN-MESSAGE").replaceAll("%player%", player.getDisplayName());
                    String raw2 = raw1.replaceAll("%participants%", String.valueOf(plugin.eventPlayers.size()));
                    String raw3 = raw2.replaceAll("%maxplayers%", "32");
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + raw3));

                    if (plugin.currentEvent.equalsIgnoreCase("spleef")) {
                        int X = plugin.getConfig().getInt("ARENAS.SPLEEF.SPAWN.X");
                        int Y = plugin.getConfig().getInt("ARENAS.SPLEEF.SPAWN.Y");
                        int Z = plugin.getConfig().getInt("ARENAS.SPLEEF.SPAWN.Z");
                        player.teleport(new Location(Bukkit.getWorld("world"), X, Y, Z));


                        saveInventory(player);
                        player.getInventory().clear();

                        ItemStack SpleefShovel = new ItemStack(Material.DIAMOND_SPADE);
                        ItemMeta ShovelMeta = SpleefShovel.getItemMeta();

                        ShovelMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lSpleef Shovel"));
                        ShovelMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
                        ShovelMeta.spigot().setUnbreakable(true);

                        SpleefShovel.setItemMeta(ShovelMeta);



                        player.getInventory().setItem(0, SpleefShovel);



                    } else {
                        if (plugin.currentEvent.equalsIgnoreCase("lms")) {
                            int X = plugin.getConfig().getInt("ARENAS.LMS.SPAWN.X");
                            int Y = plugin.getConfig().getInt("ARENAS.LMS.SPAWN.Y");
                            int Z = plugin.getConfig().getInt("ARENAS.LMS.SPAWN.Z");
                            player.teleport(new Location(Bukkit.getWorld("world"), X, Y, Z));

                            saveInventory(player);
                            player.getInventory().clear();
                        }
                     }


                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere is no active event!"));
            }
    }

    @Override
    public String name() {
        return plugin.commandManager.join;
    }

    @Override
    public String help() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

}
