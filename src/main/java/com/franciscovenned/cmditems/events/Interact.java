package com.franciscovenned.cmditems.events;

import com.franciscovenned.cmditems.CMDItems;
import com.franciscovenned.cmditems.utils.TextUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Interact implements Listener {

    private final CMDItems plugin;

    public Interact(CMDItems plugin){
        this.plugin = plugin;
    }




    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Action action = event.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){
            Material material = event.getClickedBlock().getType();
            Player player = event.getPlayer();
            player.setItemInHand(new ItemStack(material));
            TextUtils.send(player, "Se te a cambiado el bloque que tenias en la mano down");
        }
    }
}
