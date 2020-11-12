package com.franciscovenned.cmditems;

import com.franciscovenned.cmditems.commands.MainCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public final class CMDItems extends JavaPlugin {

    private final PluginDescriptionFile pdfFile = getDescription();
    private final String version = pdfFile.getVersion();
    private final String nombre = pdfFile.getName();

    private void send(String message) {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void onEnable() {
        // Inicio del plugin
        send("&2Plugin &aActivate");
        registerConfig();
        registerCommands();


    }

    @Override
    public void onDisable() {
        // Cuando se apaga el servidor o se desactiva
        send("&4Plugin &aDisabled");
    }

    private void registerConfig() {
    File config = new File(getDataFolder(),"config.yml");
        if(!config.exists()) {
        saveDefaultConfig();
        }
    }

    public void saveConfig(){
        try {
            getConfig().save(new File(getDataFolder(), "config.yml")); //Guardo el item en el yml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Registro de comandos

    private void registerCommands() {
        this.getCommand("main").setExecutor(new MainCommand(this));
    }

    // Verificar la version del plugin
    public String getVersion(){
        return this.version;
    }

}
