package com.franciscovenned.cmditems.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

public class FirtsEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Bukkit.broadcastMessage("Interactuo");
    }
}
