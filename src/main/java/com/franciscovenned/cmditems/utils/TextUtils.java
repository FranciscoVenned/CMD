package com.franciscovenned.cmditems.utils;

import com.franciscovenned.cmditems.CMDItems;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TextUtils {

    public static void send(CommandSender sender,String message){
        CMDItems plugin = CMDItems.getInstance(); // Estamos obteniendo la instancia de la mainclass
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("prefix") + " " + message));
    }
}
