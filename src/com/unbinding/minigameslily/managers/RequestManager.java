package com.unbinding.minigameslily.managers;

import com.unbinding.minigameslily.Main;
import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;
import lilypad.client.connect.api.request.impl.RedirectRequest;
import lilypad.client.connect.api.result.FutureResultListener;
import lilypad.client.connect.api.result.StatusCode;
import lilypad.client.connect.api.result.impl.RedirectResult;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class RequestManager {

    private Connect connect;
    private Main main;

    public RequestManager(Main main, Connect connect){
        this.main = main;
        this.connect = connect;
    }

    public void request(final String recipient, final String channel, final String message){
        MessageRequest request = null;
        try{
            request = new MessageRequest(recipient, channel, message);

        }catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        try{
            connect.request(request);
        }catch(RequestException ex){
            ex.printStackTrace();
        }
    }
    public void request(List<String> recipients, final String channel, final String message){
        MessageRequest request = null;
        try{
            request = new MessageRequest(recipients, channel, message);

        }catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        try{
            connect.request(request);
        }catch(RequestException ex){
            ex.printStackTrace();
        }
    }

    public void redirect(final Player player, final String server) {
        if(connect.getSettings().getUsername().equals(server)) {
            return; // probably a bad idea to redirect when you're already on this server
        }
        try {
            connect.request(new RedirectRequest(server, player.getName())).registerListener(new FutureResultListener<RedirectResult>() {
                public void onResult(RedirectResult redirectResult) {
                    if(redirectResult.getStatusCode() == StatusCode.SUCCESS) {
                        return;
                    }
                    StringManager.sendMessage(player, MM.SERVER_OFFLINE);
                }
            });
        } catch(RequestException exception) {
            // ignore
        }
    }

    public void asyncRequest(final List<String> recipients, final String channel, final String message){
        new BukkitRunnable(){
            public void run(){
                request(recipients, channel, message);
            }
        }.runTaskAsynchronously(main);
    }

    public void asyncRequest(final String recipient, final String channel, final String message){
        new BukkitRunnable(){
            public void run(){
                request(recipient, channel, message);
            }
        }.runTaskAsynchronously(main);
    }

}
