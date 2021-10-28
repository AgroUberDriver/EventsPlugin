package me.agro.events.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.agro.events.Main;

import java.io.File;
import java.io.IOException;

public class SetSpawnCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    @Override
    public void onCommand(Player player, String[] args) throws IOException {
        if(args.length==2) {
            String event = args[1];

            if (player.hasPermission("event.admin")) {
                if(event.equalsIgnoreCase("Spleef")) {
                    File file = new File(plugin.getDataFolder()+File.separator+"config.yml");
                    FileConfiguration config = plugin.getConfig();

                   config.set("ARENAS.SPLEEF.SPAWN.X", player.getLocation().getBlockX());
                   config.set("ARENAS.SPLEEF.SPAWN.Y", player.getLocation().getBlockY());
                   config.set("ARENAS.SPLEEF.SPAWN.Z", player.getLocation().getBlockZ());

                   player.sendMessage("You have set the spleef arena spawn to " + player.getLocation().getBlockX() + player.getLocation().getBlockY() + player.getLocation().getBlockY());

                   config.save(file);
                } else {
                    if(event.equalsIgnoreCase("LMS")) {
                        File file = new File(plugin.getDataFolder()+File.separator+"config.yml");
                        FileConfiguration config = plugin.getConfig();

                        config.set("ARENAS.LMS.SPAWN.X", player.getLocation().getBlockX());
                        config.set("ARENAS.LMS.SPAWN.Y", player.getLocation().getBlockY());
                        config.set("ARENAS.LMS.SPAWN.Z", player.getLocation().getBlockZ());

                        player.sendMessage("You have set the spleef arena spawn to " + player.getLocation().getBlockX() + player.getLocation().getBlockY() + player.getLocation().getBlockY());

                        config.save(file);
                    }
                }
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.INVALID-ARGUMENT")));
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.setspawn;
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
