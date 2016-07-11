package com.unbinding.minigameslily.managers;

import com.unbinding.minigameslily.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class ConfigManager {

    private ConfigManager() { }

    static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance(){
        return instance;
    }

    private Main main;
    private FileConfiguration config;
    private File cfile;

    public void setup(Main main){
        this.main = main;

        if(!main.getDataFolder().exists()) main.getDataFolder().mkdir();

        cfile = new File(main.getDataFolder(), "config.yml");

        if(!cfile.exists()){
            try{cfile.createNewFile();}
            catch(Exception e){ }
        }

        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().copyDefaults(true);
        save();
    }

    public String getString(String message){
        if(config.getString("messages." + message) != null) {
            return ChatColor.translateAlternateColorCodes('&', config.getString("messages." + message));
        }
        return null;
    }

    public String getFormat(String message){
        if(config.getString("formats." + message) != null){
            return ChatColor.translateAlternateColorCodes('&', config.getString("formats." + message));
        }
        return null;
    }

    public void save(){
        try{
            config.save(cfile);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
