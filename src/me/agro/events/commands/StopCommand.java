package me.agro.events.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.agro.events.Main;

public class StopCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    String prefix = plugin.getConfig().getString("MESSAGES.PREFIX");



    @Override
    public void onCommand(Player player, String[] args) {
        if (player.hasPermission("events.admin")) {
            if(plugin.activeEvent == true && plugin.eventPlayers.size() != 0) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("MESSAGES.EVENT-STOPPED")));
                plugin.activeEvent = false;
                plugin.eventPlayers.clear();
            } else {
                player.sendMessage(ChatColor.RED + "There is currently not an event active!");
            }
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.stop;
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
