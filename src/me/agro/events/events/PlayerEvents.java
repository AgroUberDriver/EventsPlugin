package me.agro.events.events;

import me.agro.events.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.io.IOException;

import static me.agro.events.util.EventHandler.playerDied;
import static me.agro.events.util.InventoryHandler.restoreInventory;

public class PlayerEvents implements Listener {

    private Main plugin = Main.getInstance();

    @EventHandler

    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(plugin.eventPlayers.contains(p)) {
            Material Block = e.getBlock().getType();
            if(!(Block==Material.SNOW_BLOCK)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You cannot break blocks during events!");
            } else {
                Block block = e.getBlock();
                block.setType(Material.AIR);
                e.setCancelled(true);
                ItemStack SnowBall = new ItemStack(Material.SNOW_BALL, 8);
                Inventory inv = p.getInventory();
                inv.addItem(SnowBall);
            }
        }
    }

    @EventHandler
        public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(plugin.eventPlayers.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You cannot place blocks during events!");
        }
    }

    @EventHandler
        public void onMove(PlayerMoveEvent e) throws IOException {
            Player p = e.getPlayer();
            if(plugin.eventPlayers.contains(p)) {
                Material to = e.getTo().getBlock().getType();

                if(to.equals(Material.WATER) || to.equals(Material.STATIONARY_WATER)) {
                    Location loc = Bukkit.getServer().getWorld("world").getSpawnLocation();
                    p.teleport(loc);
                    restoreInventory(p);
                    playerDied(p);
                }
            }
    }

    @EventHandler
    public void onEvent(ProjectileHitEvent event) {
        Projectile check = event.getEntity();

        //check if the list contains the projectile

            Projectile projectile = event.getEntity();
            //getting the target block
            Block block = projectile.getLocation().getBlock();
            int id = block.getTypeId();

            //this is a good way to get the block correctly
            for (double i = 0.2D; i < 4.0D; i += 0.2D) {
                if (id == 0) {
                    block = projectile.getLocation().add(projectile.getVelocity().normalize().multiply(i)).getBlock();
                    id = block.getTypeId();
                }
            }

            if (id > 0) {
                //set the block to air
                block.setType(Material.AIR);

                //make it look like the block broke
                projectile.getLocation().getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, id);
            }
        }
    }