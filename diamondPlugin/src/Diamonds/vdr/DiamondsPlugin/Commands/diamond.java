package Diamonds.vdr.DiamondsPlugin.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Items;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import Diamonds.vdr.DiamondsPlugin.Main;

public class diamond implements CommandExecutor {

	public diamond(Main main) {
	}

	public diamond() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§b[鑽石大守衛] §d==========教學==========");
			sender.sendMessage("§b/diamond help     §d |    教學清單 ✔ ");
			sender.sendMessage("§b/diamond say       §d|    每個人都可以輸入 ✔");
			sender.sendMessage("§b/diamond text      §d|    每個人都可以輸入 ✔");
			sender.sendMessage("§b/diamond give    §d  |    有op的人輸入會有鑽石 ✔ ");
			return true;
		}

		if (args[0].equals("say")) {
			sender.sendMessage("§b[鑽石大守衛]§a hi 你好你有任何事情可以輸入 /diamond help");
			return true;
		}
		if (args[0].equals("text")) {
			sender.sendMessage("§b[鑽石大守衛] §a你是在哭喔你text幹嘛啦");
			return true;

		}
		if (args[0].equals("give")) {
			if (sender instanceof Player) {
				if (!sender.isOp()) {
					sender.sendMessage("§b[鑽石大守衛]§a 此指令只有玩家才能執行");
					sender.sendMessage("§b[鑽石大守衛] §a你沒有權限執行這個指令");
				} else {
					sender.getServer().getPlayer(sender.getName()).getInventory()
							.addItem(new ItemStack(Material.DIAMOND, 64));
					return true;
				}
			}
		}

		if (args[0].equals("help")) {
			sender.sendMessage("§b[鑽石大守衛] §d==========教學==========");
			sender.sendMessage("§b/diamond help    §d  |    教學清單 ✔ ");
			sender.sendMessage("§b/diamond say    §d   |    每個人都可以輸入 ✔");
			sender.sendMessage("§b/diamond text     §d |    每個人都可以輸入 ✔");
			sender.sendMessage("§b/diamond give     §d |    有op的人輸入會有鑽石 ✔ ");
			return true;

		}

		if (args[0].equals("giveop")) {
			sender.getServer().getPlayer(sender.getName()).getInventory().addItem(new ItemStack(Material.BOOK, 1));
			ItemStack res = null;
			@SuppressWarnings("null")
			ItemMeta meta = res.getItemMeta();
			meta.setDisplayName("§c回血書");
			@SuppressWarnings("rawtypes")
			ArrayList lore = new ArrayList();
			lore.add("§b手持回血, 每秒回血1滴血");
			meta.setLore(lore);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			res.setItemMeta(meta);
			return res != null;
		}
		if (Items.equalsIgnoreCase("升級寶石"))

		{
			ItemStack 升級寶石 = new ItemStack(Material.COMMAND_BLOCK, 1);
			ItemMeta meta = 升級寶石.getItemMeta();
			meta.setDisplayName("§a升級寶石");
			@SuppressWarnings("rawtypes")
			ArrayList lore = new ArrayList();
			lore.add("§b手持該物品持續加經驗, 每0.5秒加1經驗");
			meta.setLore(lore);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			升級寶石.setItemMeta(meta);
		}

		return false;
	}

	public void runTask() {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// 遍历当前服务器所有在线的玩家
				for (Player player : Bukkit.getOnlinePlayers()) {
					// 判断玩家手上的物品是否为咱们自定义的Item
					if (player.getItemInHand().isSimilar(Items.getItem("回血书"))) {
						// 这里是要判断玩家的血量有没有超过最大血量，如果不加这个判断会报错，原因是血量持续增加但是最高血量就是20
						if (player.getMaxHealth() - player.getHealth() > 1) {
							// 设置玩家的血量为当前血量+1
							player.setHealth(player.getHealth() + 1);
						} else {
							player.sendMessage(ChatColor.YELLOW + player.getName() + " >> " + ChatColor.RED
									+ "Your health has max!");
						}
					}
				}
				// 这里是设置延迟，有个java基础的应该都懂
			}
		}.runTaskTimer((Plugin) this, 10, 10);
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getItemInHand().isSimilar(Items.getItem("升级宝石"))) {
						player.giveExp((int) (player.getExp() + 1));
					}
				}
			}
		}.runTaskTimer((Plugin) this, 10, 10);
	}

	public static boolean equalsIgnoreCase(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
