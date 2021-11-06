package me.agro.events;


import me.agro.events.commands.CommandManager;
import me.agro.events.events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;


public class Main extends JavaPlugin {

    public boolean activeEvent = false;
    public boolean eventJoinable = false;
    public ArrayList<Player> eventPlayers = new ArrayList<Player>();

    private static Main instance;

    public CommandManager commandManager;


    public void onEnable() {
        System.out.println("Enabled uEvents v0.1");
        setInstance(this);

        commandManager = new CommandManager();

        commandManager.setup();

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        File configFile = new File(this.getDataFolder() + "/config.yml");
        if(!configFile.exists())
        {
            this.saveDefaultConfig();
        }

    }

    public static Main getInstance() {
        return instance;
    }

    public void onDisable() {
        System.out.println("Disabled uEvents v0.1");
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }
}


