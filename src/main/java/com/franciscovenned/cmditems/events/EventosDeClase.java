package com.franciscovenned.cmditems.events;

import com.franciscovenned.cmditems.CMDItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventosDeClase implements Listener {

    private final CMDItems plugin;


    public EventosDeClase(CMDItems plugin){
        this.plugin = plugin;

    }


    private void send(CommandSender sender, String message) {
        String prefix = plugin.getConfig().getString("prefix");
        // Hacer que todos los mensajes tengan el Prefix por default
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + message));

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Bukkit.broadcastMessage("Interactuo");
        FileConfiguration config = plugin.getConfig();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) { //Si la accion del jugador hacer click derecho o izquierdo
            // evita ser null.

            send(event.getPlayer(), "&fEstas interactuando con " + block.getType().toString());
            send(event.getPlayer(),"Tu accion fue " + action.toString());
            config.set("Interaccion", action.toString());
            config.set("Bloque interactuado", block.getType().toString());
            plugin.saveConfig();
        }

    }


    @EventHandler
    public void onBreak(BlockBreakEvent event){
        FileConfiguration config = plugin.getConfig();
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        send(player, "Estas rompiendo " + material.toString());
        config.set("Material:", material.toString());
        plugin.saveConfig();
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        Location blockLocation = block.getLocation();
        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();
//        player.getLocation().getBlockX();
//        player.getLocation().getBlockY();
//        player.getLocation().getBlockZ();
//        player.sendMessage(ChatColor.AQUA + "Estas colocando" + player.getLocation() + player.getLocation().getBlockX() + block.getType().toString());
        send(player, "Estas colocando " + block.getType().toString() );
        send(player, "En la ubicacion " + blockX + " " +  blockY + " " +  blockZ);
//        config.set("Informacion", block.getType().toString());
        config.set("Tipo de Bloque", block.getType().toString());
        config.set("Ubicacion del Bloque.X", blockX);
        config.set("Ubicacion del Bloque.Y", blockY);
        config.set("Ubicacion del Bloque.Z", blockZ);
//        config.set("y", player.getLocation().getBlockY());
//        config.set("x", player.getLocation().getBlockX());
//        config.set("z", player.getLocation().getBlockZ());
        plugin.saveConfig();
    }
}
