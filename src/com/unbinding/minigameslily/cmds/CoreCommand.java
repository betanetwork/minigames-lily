package com.unbinding.minigameslily.cmds;

import com.unbinding.minigameslily.Main;
import com.unbinding.minigameslily.managers.StringManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public abstract class CoreCommand implements CommandExecutor {

    Main plugin;
    String command, permission, permissionDeniedMessage;
    boolean canConsoleUse;

    public CoreCommand(Main main, String command, String permission, String permissionDeniedMessage, boolean canConsoleUse){
        this.command = command;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        this.plugin = main;
        this.permissionDeniedMessage = permissionDeniedMessage;
        plugin.getCommand(command).setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(!commandLabel.equalsIgnoreCase(command)) return true;

        if(!sender.hasPermission(permission) && permission != null){
            StringManager.sendMessage(sender, permissionDeniedMessage);
            return true;
        }

        if(!(sender instanceof Player) && !canConsoleUse){
            return true;
        }

        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

}
