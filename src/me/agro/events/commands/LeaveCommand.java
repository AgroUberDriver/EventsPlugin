package me.agro.events.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import me.agro.events.Main;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static me.agro.events.util.InventoryHandler.restoreInventory;

public class LeaveCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    @Override
    public void onCommand(Player player, String[] args) throws IOException {

        if (plugin.eventPlayers.contains(player)) {
            player.sendMessage(ChatColor.GREEN + "You have successfully left the event!");
            plugin.eventPlayers.remove(player);
            restoreInventory(player);
        } else {
            player.sendMessage(ChatColor.RED + "You are not currently in an event!");
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.leave;
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
