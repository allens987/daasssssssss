package Diamonds.vdr.DiamondsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import Diamonds.vdr.DiamondsPlugin.Commands.diamond;

public class Main extends JavaPlugin{

public static Main Diamond;

    @Override
    public void onEnable() {

       Diamond = this;

       getLogger().info("server is open...");
       Bukkit.getPluginCommand("diamond").setExecutor(new diamond());
    }
    public void onDisable() {
        getLogger().info("server is cord...");    
    }
}
