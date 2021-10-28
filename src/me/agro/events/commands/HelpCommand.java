package me.agro.events.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.agro.events.Main;

public class HelpCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l/event Sub Commands"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l&m--------------------"));
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/event forcestart (Event)&7 - &eForce Starts Specific Events."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/event stop &7 - &eStops Any Ongoing Events."));
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l&m--------------------"));


    }

    @Override
    public String name() {
        return plugin.commandManager.help;
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
