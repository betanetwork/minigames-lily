package com.unbinding.minigameslily.cmds;

import com.unbinding.minigameslily.Main;
import com.unbinding.minigameslily.managers.RequestManager;
import com.unbinding.minigameslily.managers.StringManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class ServerCommand extends CoreCommand {

    private Main main;

    public ServerCommand(Main main){
        super(main, "server", "", "", false);
        this.main = main;
    }

    public void execute(CommandSender sender, String[] args){
        if(args.length == 0){
            StringManager.sendServerMessage(sender);
            return;
        }

        String serverName = "";
        for(String s : main.servers.values()){
            if(!s.equalsIgnoreCase(args[0])){
                continue;
            }
            serverName = s;
        }
        if(serverName == null){
            return;
        }
        Main.getRequestManager().redirect((Player) sender, serverName);
    }

}
