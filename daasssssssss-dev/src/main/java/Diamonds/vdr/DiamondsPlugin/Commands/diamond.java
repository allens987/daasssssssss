package Diamonds.vdr.DiamondsPlugin.Commands;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class diamond implements CommandExecutor {
    @EventHandler
    public void OnPlayerJoinEventD(PlayerJoinEvent ev){
        Player player = ev.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_SHOVEL, 1));
        ItemMeta meta = null;
        meta.setDisplayName("§c領地鏟");

    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                if (!sender.isOp()) {

                    sender.sendMessage("§b[鑽石] §d==========教學==========");
                    sender.sendMessage("§b/diamond help     §d |    教學清單 ✔ ");
                    sender.sendMessage("§b/diamond say       §d|    每個人都可以輸入 ✔");
                    sender.sendMessage("§b/diamond text      §d|    每個人都可以輸入 ✔");
                } else {
                    sender.sendMessage("§b[鑽石] §d=========管理員教學=========");
                    sender.sendMessage("§b/diamond help     §d |    教學清單 ✔ ");
                    sender.sendMessage("§b/diamond say       §d|    每個人都可以輸入 ✔");
                    sender.sendMessage("§b/diamond text      §d|    每個人都可以輸入 ✔");
                    sender.sendMessage("§b/diamond give      §d|    給予鑽石 要有op ✔");


                    return true;
                }

            }

            if (args[0].equals("say")) {
                sender.sendMessage("§b[鑽石]§a hi 你好你有任何事情可以輸入 help");
                return true;
            }
            if (args[0].equals("text")) {
                sender.sendMessage("§b[鑽石] §a你是在哭喔你text幹嘛啦");
                return true;

            }
            if (args[0].equals("server")) {
                if (!sender.isOp()) {
                    sender.sendMessage("§b[鑽石] §a你沒有權限執行這個指令");
                } else if (args.length != 2) {
                    sender.sendMessage("§b[鑽石] §a用法：/diamond server <訊息>");
                } else {
                    String Message = null;
                    for (int i = 2; i <= args.length; i++) {
                        Message = Message + args[i - 1];
                        Bukkit.getServer().broadcastMessage("§b[鑽石大公告] §e  " + Message);
                    }
                }
            }
            if (args[0].equals("give")) {
                if (sender instanceof Player) {
                    if (!sender.isOp()) {

                        sender.sendMessage("§b[鑽石大守衛]§a 此指令只有玩家才能執行");
                        sender.sendMessage("§b[鑽石大守衛] §a你沒有權限執行這個指令");
                    } else {
                        sender.getServer().getPlayer(sender.getName()).getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
                        return true;
                    }
                }
            }

            if (args[0].equals("help")) {
                if (sender instanceof Player) {
                    if (!sender.isOp()) {

                        sender.sendMessage("§b[鑽石] §d==========教學==========");
                        sender.sendMessage("§b/diamond help     §d |    教學清單 ✔ ");
                        sender.sendMessage("§b/diamond say       §d|    每個人都可以輸入 ✔");
                        sender.sendMessage("§b/diamond text      §d|    每個人都可以輸入 ✔");
                    } else {
                        sender.sendMessage("§b[鑽石] §d=========管理員教學=========");
                        sender.sendMessage("§b/diamond help     §d |    教學清單 ✔ ");
                        sender.sendMessage("§b/diamond say       §d|    每個人都可以輸入 ✔");
                        sender.sendMessage("§b/diamond text      §d|    每個人都可以輸入 ✔");
                        sender.sendMessage("§b/diamond give      §d|    給予鑽石 要有op ✔");
                        return true;

                    }
                }
                return false;
            }
        }
        return false;
    }
}

