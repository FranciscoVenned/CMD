package com.franciscovenned.cmditems;

import com.franciscovenned.cmditems.commands.MainCommand;
import com.franciscovenned.cmditems.events.EventosDeClase;
import com.franciscovenned.cmditems.events.Interact;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public final class CMDItems extends JavaPlugin {

    private final PluginDescriptionFile pdfFile = getDescription();
    private final String version = pdfFile.getVersion();
    private final String nombre = pdfFile.getName();
    private static CMDItems instance;

    private void send(String message) {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void onEnable() {
        instance = this; // Guardando la clase principal y compartirlas siendo static
        // Inicio del plugin
        send("&2Plugin &aActivate");
        registerConfig();
        registerCommands();
        registerEvents();


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

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
//        pm.registerEvents(new MisEventos(this),this);
//          pm.registerEvents(new EventosDeClase(this), this);
//          pm.registerEvents(new Interact(this), this);
    }

    //Registro de comandos

    private void registerCommands() {
        this.getCommand("main").setExecutor(new MainCommand(this));
    }

    // Verificar la version del plugin
    public String getVersion(){
        return this.version;
    }

    public static CMDItems getInstance(){
        return instance;
    }

}
