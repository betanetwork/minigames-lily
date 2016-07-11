package com.unbinding.minigameslily.cmds;

import com.unbinding.minigameslily.Main;
import lilypad.client.connect.api.Connect;
import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class GlistCommand extends CoreCommand {

    private Connect connect;
    public GlistCommand(Main main, Connect connect){
        super(main, "glist", "", "", false);
        this.connect = connect;
    }

    public void execute(CommandSender sender, String[] args){
        Main.getRequestManager().asyncRequest(Collections.<String> emptyList(), "listcall", connect.getSettings().getUsername() + ">>" + sender.getName());
    }

}
