package Diamonds.vdr.DiamondsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import Diamonds.vdr.DiamondsPlugin.Commands.diamond;

@SuppressWarnings("unused")
public class Main extends JavaPlugin{

public static Main Diamond;

    @Override
    public void onEnable() {

       Diamond = this;

       getLogger().info("����Ұʧ���...");
       Bukkit.getPluginCommand("diamond").setExecutor(new diamond());
    }
    public void onDisable() {
        getLogger().info("������������...");    
    }
}