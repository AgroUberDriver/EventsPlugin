package me.agro.events.util;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import me.agro.events.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;

public class SchematicHandler {



    public static void loadSchematic(String event) {

        Location loc = Bukkit.getServer().getWorld("world").getSpawnLocation();
        Main plugin = Main.getInstance();

        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");

        if (event.equalsIgnoreCase("Spleef")) {
            File schematic = new File(plugin.getDataFolder() + File.separator + "/schematics/spleef.schematic");

            EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 100000);
            try {
                CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
                clipboard.rotate2D(90);
                clipboard.paste(session, new Vector(loc.getX(), loc.getY(), loc.getZ()), false);
            } catch (MaxChangedBlocksException | DataException | IOException e) {
                e.printStackTrace();
            }
        } else {
            if(event.equalsIgnoreCase("LMS")) {
                File schematic = new File(plugin.getDataFolder() + File.separator + "/schematics/LMS.schematic");

                EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 100000);
                try {
                    CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
                    clipboard.rotate2D(90);
                    clipboard.paste(session, new Vector(loc.getX() + 75, loc.getY() + 30, loc.getZ() + 75), false);
                } catch (MaxChangedBlocksException | DataException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
