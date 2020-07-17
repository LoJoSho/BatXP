package me.lojosho.batsxp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BatsXP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void BatDeathXP(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        int upper = this.getConfig().getInt("minXP");
        int lower = this.getConfig().getInt("maxXP");
        if (entity.getType() == EntityType.BAT) {
            int Random = (int) (Math.random() * (upper - lower)) + lower;
            event.setDroppedExp(Random);
            if (this.getConfig().getBoolean("debug")) {
                System.out.println(Random);
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("BatXP")) {
            if (!sender.hasPermission("BatXP.reload")) {
                sender.sendMessage(ChatColor.DARK_RED + "[BatXP] Permission denied");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_AQUA + "[BatXP] Usage: /BatXP reload");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(ChatColor.DARK_GREEN + "[BatXP] BatXP has been reloaded");
                Bukkit.getServer().getLogger().info("[BatXP] Have we reloaded yet?");
                this.reloadConfig();
                Bukkit.getServer().getLogger().info("[BatXP] We have reloaded! Woo!");
            }
        }
        return false;
    }
}