package com.unbinding.minigameslily.cmds;

import com.unbinding.minigameslily.Main;
import com.unbinding.minigameslily.managers.StringManager;
import com.unbinding.minigameslily.managers.MM;
import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class DispatchCommand extends CoreCommand {


    public DispatchCommand(Main main){
        super(main, "dispatch", "admin.dispatch", MM.ADMIN_ONLY, true);
    }

    public void execute(CommandSender sender, String[] args){
        if(args.length == 0){
            StringManager.sendMessage(sender, MM.NO_ARGS_DISPATCH);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }

        String message = sb.toString().trim();

        Main.getRequestManager().asyncRequest(Collections.<String> emptyList(), "dispatch", message);

    }

}
