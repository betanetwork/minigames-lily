package com.unbinding.minigameslily.managers;

/**
 * Created by Broc.
 * Best day of my life: April 21st, 2016 <3
 */
public class MM {

    public static String ADMIN_ONLY;
    public static String MOD_ONLY;
    public static String HELPER_ONLY;
    public static String NO_ARGS_DISPATCH;
    public static String NO_ARGS_ALERT;
    public static String NO_ARGS_SERVER;
    public static String NO_ARGS_ADMINCHAT;
    public static String STAFF_CHAT_FORMAT;
    public static String SERVER_OFFLINE;
    public static String GLIST_MESSAGE;

    ConfigManager configManager;

    public MM(){
        configManager = configManager.getInstance();
        setupStrings();
    }

    public void setupStrings(){
        ADMIN_ONLY = configManager.getString("adminonly");
        MOD_ONLY = configManager.getString("modonly");
        HELPER_ONLY = configManager.getString("helperonly");
        NO_ARGS_DISPATCH = configManager.getString("noargs-dispatch");
        NO_ARGS_ALERT = configManager.getString("noargs-alert");
        NO_ARGS_ADMINCHAT = configManager.getString("noargs-adminchat");
        NO_ARGS_SERVER = configManager.getString("noargs-server");
        STAFF_CHAT_FORMAT = configManager.getFormat("staff-chat-format");
        SERVER_OFFLINE = configManager.getString("server-offline");
        GLIST_MESSAGE = configManager.getString("glist-message");
    }
}
