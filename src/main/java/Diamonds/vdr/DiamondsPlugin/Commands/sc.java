package Diamonds.vdr.DiamondsPlugin.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class sc implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player joined = evt.getPlayer();
        String joinedName = joined.getName();

        //RETRIEVING THE JOIN MESSAGE ALREADY SET
        String joinMessage = evt.getJoinMessage();

        //SETTING THE JOIN MESSAGE
        evt.setJoinMessage(joinedName + " has joined the game");

        //CLEARING THE JOIN MESSAGE
        evt.setJoinMessage(null);
    }
}
