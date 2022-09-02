
package Diamonds.vdr.DiamondsPlugin.Commands;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class sc implements CommandExecutor {
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
    }
