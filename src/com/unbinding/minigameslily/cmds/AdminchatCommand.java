package com.unbinding.minigameslily.cmds;

import com.unbinding.minigameslily.Main;
import com.unbinding.minigameslily.managers.MM;
import com.unbinding.minigameslily.managers.StringManager;
import org.bukkit.command.CommandSender;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class AdminchatCommand extends CoreCommand {

    public AdminchatCommand(Main main){
        super(main, "a", "staff.adminchat", MM.HELPER_ONLY, true);
    }

    public void execute(CommandSender sender, String[] args){
        if(args.length == 0){
            StringManager.sendMessage(sender, MM.NO_ARGS_ADMINCHAT);
            return;
        }

        String message = "";
        for(String arg : args){
            message += arg + " ";
        }

        StringManager.sendStaffchatMessage(sender, message);
    }

}
