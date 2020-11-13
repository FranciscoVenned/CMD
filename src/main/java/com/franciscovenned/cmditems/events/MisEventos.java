package com.franciscovenned.cmditems.events;

import com.franciscovenned.cmditems.CMDItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.io.File;

public class MisEventos implements Listener {
    final private CMDItems plugin;

    public MisEventos(CMDItems plugin){
        this.plugin = plugin;

    }

//
//    private void send(CommandSender sender, String message) {
//        FileConfiguration config = plugin.getConfig();
//        // LLamando al prefix establecido en el plugin.yml
//        String prefix = config.getString("prefix");
//        // Hacer que todos los mensajes tengan el Prefix por default
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + message));
//
//    }
//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent event){
//        Bukkit.broadcastMessage("Interactuo");
//        FileConfiguration config = plugin.getConfig();
//        Action interact = event.getAction();
//        Block interact2 = event.getClickedBlock();
//        Player player = event.getPlayer();
//        send(event.getPlayer(), "&fEstas interactuando con " + interact + interact2);
//        config.set("Interaccion", interact);
//        config.set("Bloque interactuado", interact2);
//        plugin.saveConfig();
//
//
//    }
//
//    @EventHandler
//    public void onBreak(BlockBreakEvent event){
//        FileConfiguration config = plugin.getConfig();
//        Block block = event.getBlock();
//        Material material = block.getType();
//        Player player = event.getPlayer();
//        player.sendMessage(ChatColor.AQUA + "Estas rompiendo" + ChatColor.YELLOW + material);
//        config.set("Material:", material);
//        plugin.saveConfig();
//    }
//    @EventHandler
//    public void onPlace(BlockPlaceEvent event){
//        FileConfiguration config = plugin.getConfig();
//        Player player = event.getPlayer();
//        Block block = event.getBlockPlaced();
//        player.getLocation().getBlockX();
//        player.getLocation().getBlockY();
//        player.getLocation().getBlockZ();
//        player.sendMessage(ChatColor.AQUA + "Estas colocando" + player.getLocation() + player.getLocation().getBlockX() + block);
//        config.set("Informacion", block);
//        config.set("y", player.getLocation().getBlockY());
//        config.set("x", player.getLocation().getBlockX());
//        config.set("z", player.getLocation().getBlockZ());
//        plugin.saveConfig();
//    }
}
