package me.agro.events.commands;

import org.bukkit.entity.Player;

import java.io.IOException;

public abstract class SubCommandHandler {

    public SubCommandHandler() {

    }

    public abstract void onCommand(Player player, String[] args) throws IOException;

    public abstract String name();

    public abstract String help();

    public abstract String[] aliases();
}
