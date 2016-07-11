package com.unbinding.minigameslily.listeners;

import com.unbinding.minigameslily.Main;
import com.unbinding.minigameslily.managers.MM;
import com.unbinding.minigameslily.managers.StringManager;
import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;
import org.bukkit.entity.Player;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class MessageListener {

    private Main main;
    private Connect connect;

    public MessageListener(Main main, Connect connect){
        this.main = main;
        this.connect = connect;
    }

    @EventListener
    public void onMessage(MessageEvent event) {
        if(event.getChannel().equals("syncserverscall")){
            Main.getRequestManager().asyncRequest(Collections.<String>emptyList(), "syncserversresponse", connect.getSettings().getUsername());
        }else if(event.getChannel().equals("syncserversresponse")){
            try{
                String message = event.getMessageAsString();
                main.servers.put(message, message);
            }catch(UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }else if(event.getChannel().equals("dispatch")){
            try{
                String message = event.getMessageAsString();
                message = message.replaceAll("/", "");
                main.getServer().dispatchCommand(main.getServer().getConsoleSender(), message);
            }catch(UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }else if(event.getChannel().equals("adminchat")){
            try{
                String message = event.getMessageAsString();
                for(Player pl : main.getServer().getOnlinePlayers()){
                    if(!pl.hasPermission("staff.adminchat")){
                        continue;
                    }
                    pl.sendMessage(message);
                }
            }catch(UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }else if(event.getChannel().equals("listcall")){
            String[] data;
            String server;
            String player;
            int count;
            try{
                data = event.getMessageAsString().split(">>");
                server = data[0];
                player = data[1];

                count = main.getServer().getOnlinePlayers().size();

                Main.getRequestManager().asyncRequest(server, "list", player + ">>" + count);
            }catch(UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }else if(event.getChannel().equals("list")){
            String[] data;
            String player;
            String count;
            int totalCount = 0;
            try{
                data = event.getMessageAsString().split(">>");
                player = data[1];
                count = data[2];
                totalCount += Integer.parseInt(count);

                Player p = main.getServer().getPlayer(player);
                if(p != null){
                    StringManager.sendMessage(p, StringManager.colorize(StringManager.purifyMessage(MM.GLIST_MESSAGE, null, null, null, totalCount, null)));
                }
            }catch(UnsupportedEncodingException ex){

            }

        }

    }

}
