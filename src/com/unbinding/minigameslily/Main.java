package com.unbinding.minigameslily;

import java.util.Collections;
import java.util.HashMap;

import com.unbinding.minigameslily.cmds.AdminchatCommand;
import com.unbinding.minigameslily.cmds.DispatchCommand;
import com.unbinding.minigameslily.cmds.GlistCommand;
import com.unbinding.minigameslily.cmds.ServerCommand;
import com.unbinding.minigameslily.listeners.MessageListener;
import com.unbinding.minigameslily.managers.ConfigManager;
import com.unbinding.minigameslily.managers.MM;
import com.unbinding.minigameslily.managers.RequestManager;
import org.bukkit.plugin.java.JavaPlugin;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;

public class Main extends JavaPlugin {

    static RequestManager requestManager;
    public HashMap<String, String> servers;
    static Connect connect;
    MM mm;
    public int totalPlayerCount;

    @Override
    public void onEnable(){
        ConfigManager.getInstance().setup(this);
        connect = this.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
        servers = new HashMap<String, String>();
        connect.registerEvents(new MessageListener(this, connect));
        requestManager = new RequestManager(this, connect);
        mm = new MM();
        totalPlayerCount = 0;
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
                syncServers();
            }
        }, 0, 20);
        try{
            registerCommands();
        }catch(Exception e){
            getLogger().severe(e.getMessage());
        }

    }

    public void syncServers(){
        servers.clear();
        getRequestManager().asyncRequest(Collections.<String> emptyList(), "syncserverscall", "");
    }

    public static Connect getConnect(){
        return connect;
    }

    public static RequestManager getRequestManager(){
        return requestManager;
    }

    private void registerCommands(){
        new DispatchCommand(this);
        new AdminchatCommand(this);
        new ServerCommand(this);
        new GlistCommand(this, connect);
    }



}

