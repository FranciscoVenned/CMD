package com.franciscovenned.cmditems.commands;

import com.franciscovenned.cmditems.CMDItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand implements CommandExecutor {
    final private CMDItems plugin;
//    private CommandSender sender;

    public MainCommand(CMDItems plugin) {
        this.plugin = plugin;
    }

    private void send(CommandSender sender, String message) {
        FileConfiguration config = plugin.getConfig();
        // LLamando al prefix establecido en el plugin.yml
        String prefix = config.getString("prefix");
        // Hacer que todos los mensajes tengan el Prefix por default
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + message));

    }


//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        FileConfiguration config = plugin.getConfig();
//        this.sender = sender;
//        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
//            send("&6Lista de comandos");
//            send("&f/" + label + " help");
//            send("&f/" + label + " version");
//            send("&f/" + label + " reload");
//            send("&f/" + label + " give (player) (tipo) (nivel)");
//            send("&f/" + label + " giveFuel (player) (tipo) <cantidad>");
//            send("&f/" + label + " editDrops");
//            send("&f/" + label + " list (player/tipo)");
//            send("&f/" + label + " shop");
//            send("&f/" + label + " toggleAvisos");
//
//
//            //Comando para verificar version del plugin
//        } else if (args[0].equalsIgnoreCase("version")) {
//            send("version: " + plugin.getVersion());
//
//
//            //Comando para la tienda del servidor
//        } else if (args[0].equalsIgnoreCase("shop")) {
//            if (sender instanceof ConsoleCommandSender) {
//                send("No puedes abrir la shop desde la consola pelotudo");
//
//                return true; //No sigue leyendo las lineas siguientes.
//            }   // "!" signo de negacion osea si el sender no tiene permisos
//            if (!sender.hasPermission("cmditems.shop")) {
//                send("No tienes permisos down");
//                return true; //obligatorio el return por que sino pasara por encima
//
//            }
//            //Si la cantidad de argumentos es menor a se ejecuta esta linea
//            if (args.length < 2) {
//                send("necesitas mas argumentos");
//
//                return true;
//            }
//            Player player2 = Bukkit.getPlayer(args[1]);
//            // /m shop pancho
//            if (player2 == null) {
//                send("Ese jugador no existe o no esta conectado");
//                return true;
//            }
//            //Player = El tipo que le queremos ofrecer al metodo "castear"
//            Player player = (Player) sender;
//            send("Shop abierto para " + player2.getName());
//
//        } else  if ()
//
//        {
//
//        } else if (args[0].equalsIgnoreCase("stop")) {
//            send("server apagado");
//           plugin.getServer().shutdown();
//        }
//
//
//        return true;
//    }
    // comando con switch

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        this.sender = sender;  (Esto puede perjudicar si hay muchas personas usando dicho comandos)

        FileConfiguration config = plugin.getConfig();
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            send(sender, "&6Lista de comandos");
            send(sender, "&f/" + label + " help");
            send(sender, "&f/" + label + " version");
            send(sender, "&f/" + label + " reload");
            send(sender, "&f/" + label + " giveItem (NAME)");
            send(sender, "&f/" + label + " createItem (MATERIAL) (cantidad) (Name) (Linea1 Linea2 Linea3) ");
            send(sender, "&f/" + label + " createItem2 (MATERIAL) (cantidad) (Name) (Linea 1, Linea 2, Linea 3) ");
            send(sender, "&f/" + label + " shop");
            return true;
        }
        switch (args[0]) {


            //Comando para verificar version del plugin
//    } else if (args[0].equalsIgnoreCase("version")) {
            case "version":
                send(sender, "version: " + plugin.getVersion());
                break;

            //Comando para la tienda del servidor
//    } else if (args[0].equalsIgnoreCase("shop")) {
            case "shop":
                if (sender instanceof ConsoleCommandSender) {
                    send(sender, "No puedes abrir la shop desde la consola pelotudo");

                    return true; //No sigue leyendo las lineas siguientes.
                }   // "!" signo de negacion osea si el sender no tiene permisos
                if (!sender.hasPermission("cmditems.shop")) {
                    send(sender, "No tienes permisos down");
                    return true; //obligatorio el return por que sino pasara por encima

                }
                //Si la cantidad de argumentos es menor a se ejecuta esta linea
                if (args.length < 2) {
                    send(sender, "necesitas mas argumentos");

                    return true;
                }
                Player player2 = Bukkit.getPlayer(args[1]);
                // /m shop pancho
                if (player2 == null) {
                    send(sender, "Ese jugador no existe o no esta conectado");
                    return true;
                }
                //Player = El tipo que le queremos ofrecer al metodo "castear"
                Player player = (Player) sender;
                send(sender, "Shop abierto para " + player2.getName());
                break;

            case "give":
                if (sender instanceof ConsoleCommandSender) {
                    send(sender, "No puedes enviar objetos desde la consola");
                    return true;
                }
                if (!sender.hasPermission("cmditems.give")) {
                    send(sender, "No tienes permisos");
                    return true;
                }
                if (args.length < 2) {
                    send(sender, "Necesitas colocar al jugador");
                    return true;
                }
                Player player3 = Bukkit.getPlayer(args[1]);
                if (player3 == null) {
                    send(sender, "Ese jugandor no esta conectado");
                    return true;
                }
                if (args.length < 3) {
                    send(sender, "Coloca el id del bloque");
                    return true;
                }
                if (args.length < 4) {
                    send(sender, "Coloca la cantidad");
                    return true;
                }
                Material tipodeitem;
                try {  //Es para evitar que salga un error en la consola y controlar cuando va haber un error en el complemento.
                    tipodeitem = Material.valueOf(args[2]);
                } catch (Exception exception) {
                    send(sender, "Ese material no es valido");
                    return true;

                }
                int cantidad;
                try {
                    cantidad = Integer.parseInt(args[3]);
                } catch (NumberFormatException exception) {
                    send(sender, "Ese no es un numero valido");
                    return true;
                }
                ItemStack itemStack = new ItemStack(tipodeitem, cantidad); // Dos valores establecidos en el constructor
                player3.getInventory().addItem(itemStack);
                break;

            case "stop":
                send(sender, "server apagado");
                plugin.getServer().shutdown();
                break;
            // default como un else
            case "prueba":
                if (args.length < 2) {
                    send(sender, "requiere mas argumentos");
                    return true;
                }
                // Una lista de Strings de camino a la config.yml
                if (config.contains(args[1])) {
                    send(sender, config.getString(args[1]));
                    return true;
                }
                break;
            case "get":
                if (!sender.hasPermission("cmditems.get")) {
                    send(sender, "No tienes permisos");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender) {  //Se fija en la Consola
                    send(sender, "No se puede enviar ese comando desde la consola");
                    return true;
                }
                Player player4 = (Player) sender; //Transformar el sender en un player
                String tipo = config.getString("item.tipo");
                int cantidad2 = config.getInt("item.cantidad");
                String nombre = config.getString("item.name");
                List<String> lore = config.getStringList("item.lore"); // Por si un item tiene una lore
                ItemStack item = new ItemStack(Material.valueOf(tipo), cantidad2); // Convierte la String en un Material "valaueof"
                ItemMeta meta = item.getItemMeta(); // Contiene toda las caracteristicas (Lore, name, encantamientos)
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nombre));
                List<String> lorecolores = new ArrayList<>(); //Crear una nueva lista
                for (String linea : lore) {
                    lorecolores.add(ChatColor.translateAlternateColorCodes('&', linea)); //De la lista de la config "Lore" las pasa a otra nueva linea creada por ArrayList
                }
                meta.setLore(lorecolores); //Establecer la nueva lista creada por Arraylist
                item.setItemMeta(meta);
                player4.getInventory().addItem(item);
                send(sender, "Has obtenido el item con exito");
                break;
            case "espada":
                if (!sender.hasPermission("cmditems.espada")) {
                    send(sender, "No tienes permisos para la espada");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender) {
                    send(sender, "No puedes ejecutar el siguiente comando en la consola");
                    return true;
                }
                Player player5 = (Player) sender;
                String tipo2 = config.getString("espada.tipo");
                int cantidad3 = config.getInt("espada.cantidad");
                String nombre2 = config.getString("espada.name");
                List<String> lore2 = config.getStringList("espada.lore");
                ItemStack item2 = new ItemStack(Material.valueOf(tipo2), cantidad3);
                ItemMeta meta2 = item2.getItemMeta();
                meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', nombre2));
                List<String> lorecolores2 = new ArrayList<>();
                for (String linea : lore2) {
                    lorecolores2.add(ChatColor.translateAlternateColorCodes('&', linea));
                }
                meta2.setLore(lorecolores2);
                item2.setItemMeta(meta2);
                player5.getInventory().addItem(item2);
                send(sender, "Obtuviste el item con exito");
                break;


            case "createItem":
                if (!sender.hasPermission("cmditem.give")) {
                    send(sender, "No tienes permisos");
                    return true;
                }
                // Material, Cantidad, Nombre, Lineas de Lore.
                if (args.length < 5) {
                    send(sender, "El uso correcto es EspadaModificada, Material Cantidad, Nombre");
                    return true;
                }
                String espadamodificada = args[1];
                int cantidad5 = Integer.parseInt(args[2]);  // Cantidad
                String nombreespada = args[3];
                List<String> lore5 = new ArrayList<>();  // CREA UNA LISTA DE STRING
                for (int i = 4; i < args.length; i++) {  //
                    // i++ es lo mismo que i += 1 y es lo mismo que i = i + 1
                    // int i = 4 (Apartir de la linea 4 se creara la lore cada espacio es una nueva linea con la operacion)
                    lore5.add(args[i]); // Agrega todas las lores apartir del argumento "i"
                }

                config.set(nombreespada + ".tipo", espadamodificada);
                config.set(nombreespada + ".cantidad", cantidad5);
                config.set(nombreespada + ".name", nombreespada);
                config.set(nombreespada + ".lore", lore5);

                plugin.saveConfig(); // Llama el metodo de la main de guardar todo en la config.
                send(sender, "Espada Guardada");
                break;
            case "createItem2":
                if (!sender.hasPermission("cmditem.give")) {
                    send(sender, "No tienes permisos");
                    return true;
                }
                // Material, Cantidad, Nombre, Lineas de Lore.
                if (args.length < 5) {
                    send(sender, "El uso correcto es EspadaModificada, Material Cantidad, Nombre");
                    return true;
                }
                String itemCreate = args[1];
                int quantity = Integer.parseInt(args[2]);  // Cantidad
                String type = args[3];
                StringBuilder scb = new StringBuilder();
                for (int i = 4;i < args.length;i++){     // CREAR UN OBJETO STRING BUILDER Y JUNTAR STRINGS Y JUNTA TODOS LOS ARGUMENTOS EN 4
                    scb.append(args[i]).append(" ");                // STRINGBUILDER SIRVE PARA JUNTAR UNA LISTA DE STRINGS EN UN FOR
                }
                List<String> lores = Arrays.asList(scb.toString().split(","));

                config.set(itemCreate + ".name",itemCreate);
                config.set(itemCreate + ".cantidad",quantity);
                config.set(itemCreate + ".tipo",type);
                config.set(itemCreate + ".lore",lores);


//                String construida = scb.toString(); // Convierte todo lo que agregue en la stringbuilder en una string
//                String[] lineas = construida.split(","); // Va separar una string si encuentra una ","
//                List<String> lores = Arrays.asList(lineas);

//                for (int i = 4; i < args.length; i++) {  //
//                    // i++ es lo mismo que i += 1 y es lo mismo que i = i + 1
//                    // int i = 4 (Apartir de la linea 4 se creara la lore cada espacio es una nueva linea con la operacion)
//                    lores.add(args[i]); // Agrega todas las lores apartir del argumento "i"
//                }

//                config.set(nombreespada + ".tipo", espadamodificada);
//                config.set(nombreespada + ".cantidad", quantity);
//                config.set(nombreespada + ".name", nameItem);
//                config.set(nombreespada + ".lore", lores);

                plugin.saveConfig(); // Llama el metodo de la main de guardar todo en la config.
                send(sender, "Espada Guardada");
                break;


            case "giveItem":
                if (!sender.hasPermission("cmditems.espada")) {
                    send(sender, "No tienes permisos para la espada");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender) {
                    send(sender, "No puedes ejecutar el siguiente comando en la consola");
                    return true;

                }
                if (args.length < 2) {
                    send(sender,"Falta tener 2 argumentos");
                    return true;
                }
                String nombreEspada = args[1];
                if (!config.contains(nombreEspada)){  // Verifica si contiene el nombre del Item en el argumento 1
                    send(sender,"Esa espada no existe");
                    return true;
                }
                Player player6 = (Player) sender;
                String tipo3 = config.getString(nombreEspada + ".tipo");
                int cantidad4 = config.getInt(nombreEspada + ".cantidad");
                String nombre3 = config.getString(nombreEspada + ".name");
                List<String> lore3 = config.getStringList(nombreEspada + ".lore");
                ItemStack item3 = new ItemStack(Material.valueOf(tipo3), cantidad4);
                ItemMeta meta3 = item3.getItemMeta();
                meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', nombre3));
                List<String> lorecolores3 = new ArrayList<>();
                for (String linea : lore3) {
                    lorecolores3.add(ChatColor.translateAlternateColorCodes('&', linea));
                }
                meta3.setLore(lorecolores3);
                item3.setItemMeta(meta3);
                player6.getInventory().addItem(item3);
                send(sender, "Obtuviste el item con exito");
                break;

            case "setbloque":
                if (args.length < 2) {
                    send(sender,"Usa bien el comando");
                    return true;
                }
                Location loc = (Location) config.get("Location"); //Transformacion el Obj en un Location (Se podria romper si no estas seguro)
                loc.getBlock().setType(Material.valueOf(args[1]));
                send(sender,"Cambiamos el tipo de Bloque");
                break;


            case "getItem":
                if (!sender.hasPermission("cmditems.espada")) {
                    send(sender, "No tienes permisos para givear item");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender) {
                    send(sender, "No puedes ejecutar el siguiente comando en la consola");
                    return true;
                }
                if (args.length < 2) {
                    send(sender, "Usa bien el comando -.-");
                    return true;
                }
                if (!config.contains("items." + args[1])){
                    send(sender, "No existe ese item");
                return true;
                }
                ItemStack item7 = new ItemStack(Material.valueOf(config.getString("items." + args[1] + ".item.type")), 1);
                ItemMeta meta4 = item7.getItemMeta();
                meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("items." + args[1] + ".item.name")));
                List<String> lore4 = config.getStringList( "items." + args[1] + ".item.lore");
                List<String> lorecolores4 = new ArrayList<>();
                for (String linea : lore4) {
                    lorecolores4.add(ChatColor.translateAlternateColorCodes('&', linea));
                }
                meta4.setLore(lorecolores4);
                item7.setItemMeta(meta4);
                send(sender, "Se te acaba de entregar el item " + args[1]);
                ((Player) sender).getInventory().addItem(item7);
                break;

            default:
                send(sender, "comando desconocido");


        }
        return true;
    }

}

