package me.agro.events.commands;

import me.agro.events.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static me.agro.events.util.EventHandler.StartEvent;

public class ForceStartCommand extends SubCommandHandler{

    private Main plugin = Main.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length==2) {
            String event = args[1];
            if (plugin.activeEvent == false ) {

                if(event.equalsIgnoreCase("spleef")) {
                    player.sendMessage(ChatColor.GREEN + "You have successfully started a Spleef event!");
                    StartEvent("Spleef");
                } else if(event.equalsIgnoreCase("LMS")) {
                    player.sendMessage(ChatColor.GREEN + "You have succesfully started a LMS event!");
                    StartEvent("LMS");
                } else {
                    player.sendMessage(ChatColor.RED + "You did not specify a valid event!");
                }
            } else {
                player.sendMessage(ChatColor.RED  + "There is already an active event!");
            }
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.forcestart;
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



