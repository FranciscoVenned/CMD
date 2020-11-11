package com.franciscovenned.cmditems.commands;

import com.franciscovenned.cmditems.CMDItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {
    final private CMDItems plugin;
//    private CommandSender sender;

    public MainCommand(CMDItems plugin) {
        this.plugin = plugin;
    }

    private void send(CommandSender sender,String message) {
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
            send(sender,"&6Lista de comandos");
            send(sender,"&f/" + label + " help");
            send(sender,"&f/" + label + " version");
            send(sender,"&f/" + label + " reload");
            send(sender,"&f/" + label + " give (player) (tipo) (nivel)");
            send(sender,"&f/" + label + " giveFuel (player) (tipo) <cantidad>");
            send(sender,"&f/" + label + " editDrops");
            send(sender,"&f/" + label + " list (player/tipo)");
            send(sender,"&f/" + label + " shop");
            send(sender,"&f/" + label + " toggleAvisos");
            return true;
        }
        switch (args[0]) {


            //Comando para verificar version del plugin
//    } else if (args[0].equalsIgnoreCase("version")) {
            case "version":
                send(sender,"version: " + plugin.getVersion());
                break;

            //Comando para la tienda del servidor
//    } else if (args[0].equalsIgnoreCase("shop")) {
            case "shop":
                if (sender instanceof ConsoleCommandSender) {
                    send(sender,"No puedes abrir la shop desde la consola pelotudo");

                    return true; //No sigue leyendo las lineas siguientes.
                }   // "!" signo de negacion osea si el sender no tiene permisos
                if (!sender.hasPermission("cmditems.shop")) {
                    send(sender,"No tienes permisos down");
                    return true; //obligatorio el return por que sino pasara por encima

                }
                //Si la cantidad de argumentos es menor a se ejecuta esta linea
                if (args.length < 2) {
                    send(sender,"necesitas mas argumentos");

                    return true;
                }
                Player player2 = Bukkit.getPlayer(args[1]);
                // /m shop pancho
                if (player2 == null) {
                    send(sender,"Ese jugador no existe o no esta conectado");
                    return true;
                }
                //Player = El tipo que le queremos ofrecer al metodo "castear"
                Player player = (Player) sender;
                send(sender,"Shop abierto para " + player2.getName());
                break;

            case "give":
                if (sender instanceof ConsoleCommandSender) {
                    send(sender,"No puedes enviar objetos desde la consola");
                    return true;
                }
                if (!sender.hasPermission("cmditems.give")) {
                    send(sender,"No tienes permisos");
                    return true;
                }
                if (args.length < 2) {
                    send(sender,"Necesitas colocar al jugador");
                    return true;
                }
                Player player3 = Bukkit.getPlayer(args[1]);
                if (player3 == null) {
                    send(sender,"Ese jugandor no esta conectado");
                    return true;
                }
                if (args.length < 3) {
                    send(sender,"Coloca el id del bloque");
                    return true;
                }
                if (args.length < 4) {
                    send(sender,"Coloca la cantidad");
                    return true;
                }
                Material tipodeitem;
                try {  //Es para evitar que salga un error en la consola y controlar cuando va haber un error en el complemento.
                    tipodeitem = Material.valueOf(args[2]);
                } catch (Exception exception) {
                    send(sender,"Ese material no es valido");
                    return true;

                }
                int cantidad;
                try {
                    cantidad = Integer.parseInt(args[3]);
                } catch (NumberFormatException exception) {
                    send(sender,"Ese no es un numero valido");
                    return true;
                }
                ItemStack itemStack = new ItemStack(tipodeitem, cantidad); // Dos valores establecidos en el constructor
                player3.getInventory().addItem(itemStack);
                break;

            case "stop":
                send(sender,"server apagado");
                plugin.getServer().shutdown();
                break;
            // default como un else
            case "prueba":
                if (args.length < 2) {
                    send(sender,"requiere mas argumentos");
                    return true;
                }
                // Una lista de Strings de camino a la config.yml
                if (config.contains(args[1])) {
                    send(sender,config.getString(args[1]));
                    return true;
                }
                break;
            case "get":
                if (!sender.hasPermission("cmditems.get")) {
                    send(sender,"No tienes permisos");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender) {  //Se fija en la Consola
                    send(sender,"No se puede enviar ese comando desde la consola");
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
                for (String linea : lore){
                    lorecolores.add(ChatColor.translateAlternateColorCodes('&', linea)); //De la lista de la config "Lore" las pasa a otra nueva linea creada por ArrayList
                }
                meta.setLore(lorecolores); //Establecer la nueva lista creada por Arraylist
                item.setItemMeta(meta);
                player4.getInventory().addItem(item);
                send(sender,"Has obtenido el item con exito");
                break;
            case "espada":
                if (!sender.hasPermission("cmditems.espada")) {
                    send(sender,"No tienes permisos para la espada");
                    return true;
                }
                if (sender instanceof ConsoleCommandSender){
                    send(sender,"No puedes ejecutar el siguiente comando en la consola");
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
                for (String linea : lore2){
                    lorecolores2.add(ChatColor.translateAlternateColorCodes('&', linea));
                }
                meta2.setLore(lorecolores2);
                item2.setItemMeta(meta2);
                player5.getInventory().addItem(item2);
                send(sender,"Obtuviste el item con exito");
                break;
            case "espadamodificada":
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
                for (int i = 4; i < args.length; i ++ ){  //
                                                // i++ es lo mismo que i += 1 y es lo mismo que i = i + 1
                    // int i = 4 (Apartir de la linea 4 se creara la lore cada espacio es una nueva linea con la operacion)
                    lore5.add(args[i]); // Agrega todas las lores apartir del argumento "i"
                }

                config.set(nombreespada + ".tipo",espadamodificada);
                config.set(nombreespada + ".cantidad",cantidad5);
                config.set(nombreespada + ".name",nombreespada);
                config.set(nombreespada + ".lore",lore5);

                try {
                    config.save(new File(plugin.getDataFolder(),"config.yml")); //Guardo el item en el yml
                } catch (IOException e) {
                    e.printStackTrace();
                }
                send(sender,"Espada Guardada");
                break;

            default:
                send(sender,"comando desconocido");


        }
        return true;
    }
}

