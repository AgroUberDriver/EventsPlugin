package me.agro.events.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import me.agro.events.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static me.agro.events.events.EventScheduler.getTime;
import static me.agro.events.util.EventHandler.StartEvent;
import static me.agro.events.util.SchematicHandler.loadSchematic;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommandHandler> commands = new ArrayList<SubCommandHandler>();

    private Main plugin = Main.getInstance();

    public CommandManager() {
    }


    public String main = "event";
    public String help = "help";
    public String forcestart = "forcestart";
    public String join = "join";
    public String leave = "leave";
    public String stop = "stop";
    public String setspawn = "setspawn";

    public void setup() {
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand());
        this.commands.add(new ForceStartCommand());
        this.commands.add(new JoinCommand());
        this.commands.add(new LeaveCommand());
        this.commands.add(new StopCommand());
        this.commands.add(new SetSpawnCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is not a console command!");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                openEventMenu(player);
                return true;
            }

            SubCommandHandler target = this.get(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.INVALID-SUBCOMMAND")));
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try {
                target.onCommand(player, args);
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "An internal error has occured.");

                e.printStackTrace();
            }
        }

        return true;
    }

    private SubCommandHandler get(String name) {
        Iterator<SubCommandHandler> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommandHandler sc = (SubCommandHandler) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;

            int length = (aliases = sc.aliases()).length;


            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }
            }
        }
        return null;
    }


    public void openEventMenu(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&a&lEvents Schedule"));

        ItemStack Spleef  = new ItemStack(Material.SNOW_BALL);
        ItemMeta SpleefMeta = Spleef.getItemMeta();

        ItemStack LMS = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta LMSMeta = LMS.getItemMeta();

        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.translateAlternateColorCodes('&', getTime("Spleef")));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Type &e/event join &6to compete and win an Event Box!"));

        SpleefMeta.setLore(lore);
        SpleefMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&lSpleef"));

        Spleef.setItemMeta(SpleefMeta);

        ArrayList<String> lmslore = new ArrayList();
        lmslore.add(ChatColor.translateAlternateColorCodes('&', getTime("LMS")));
        lmslore.add("");
        lmslore.add(ChatColor.translateAlternateColorCodes('&', "&6Type &e/event join &6to compete and win an Event Box!"));

        LMSMeta.setLore(lmslore);
        LMSMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&lLMS"));

        LMS.setItemMeta(LMSMeta);


        inv.setItem(2,Spleef);
        inv.setItem(6,LMS);

        p.openInventory(inv);
    }
}

