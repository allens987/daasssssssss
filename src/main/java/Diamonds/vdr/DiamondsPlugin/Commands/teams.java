import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class TeamCommand implements CommandExecutor {

    private Scoreboard teamScoreboard;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("teams")) {
            if (args.length == 0) {
                sender.sendMessage("/teams init 初始化");
                sender.sendMessage("/teams list 列出所有队伍");
                sender.sendMessage("/teams set <玩家名> <队伍名> 将玩家的队伍进行设置");
                sender.sendMessage("/teams prefix <队伍名> <前缀名> 将玩家的队伍进行前缀的设置");
                sender.sendMessage("/teams suffix <队伍名> <前缀名> 将玩家的队伍进行前缀的设置");
                return true;
            }

            if (args[0].equalsIgnoreCase("init")) {
                teamScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Team redTeam = teamScoreboard.registerNewTeam("RED");
                Team blueTeam = teamScoreboard.registerNewTeam("BLUE");

                // 设置显示名
                redTeam.setDisplayName("红队");
                blueTeam.setDisplayName("蓝队");

                // 设置队伍颜色
                redTeam.setColor(ChatColor.RED);
                blueTeam.setColor(ChatColor.BLUE);

                // 对于自己的队伍进行NameTag显示, 而对其他队伍关闭 -> 制作出类似吃鸡队友的感觉
                // 这里的FOR_OTHER_TEAM表示的意思是只对其他队伍 关闭
                redTeam.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);
                blueTeam.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);

                // 对于自己的队伍开启防碰撞体积, 而对其他队伍开启体积碰撞
                // 这里的FOR_OWN_TEAM表示的意思是只对本队 关闭
                redTeam.setOption(Option.COLLISION_RULE, OptionStatus.FOR_OWN_TEAM);
                blueTeam.setOption(Option.COLLISION_RULE, OptionStatus.FOR_OWN_TEAM);

                // 由于只做演示, 所以这里的sender我直接强转得到
                Player player = (Player) sender;
                player.setScoreboard(teamScoreboard);
                sender.sendMessage("§a操作成功!");
                return true;
            }

            if (args[0].equalsIgnoreCase("list")) {
                teamScoreboard.getTeams().forEach(team -> {
                    sender.sendMessage("名字: " + team.getName());
                    sender.sendMessage("展示名: " + team.getDisplayName());
                    sender.sendMessage("已有队员: ");
                    team.getEntries().forEach(player -> {
                        sender.sendMessage(" - " + player);
                    });
                    sender.sendMessage("=====================");
                });
                sender.sendMessage("§a操作成功!");
                return true;
            }

            if (args[0].equalsIgnoreCase("set")) {
                Player entry = Bukkit.getPlayer(args[1]);
                if (entry == null || !entry.isOnline()) {
                    sender.sendMessage("玩家不在线!");
                    return true;
                }
                Team playerTeam = teamScoreboard.getEntryTeam(entry.getName());
                Team team = teamScoreboard.getTeam(args[2]);

                if (playerTeam != null) {
                    // 将玩家离开之前的队伍
                    playerTeam.removeEntry(args[1]);
                }

                // 将玩家加入选定的队伍
                team.addEntry(args[1]);

                // 对选中的人设置计分板, 不然会导致无法显示的问题
                entry.setScoreboard(teamScoreboard);
                sender.sendMessage("§a操作成功!");
                return true;
            }

            if (args[0].equalsIgnoreCase("prefix")) {
                Team team = teamScoreboard.getTeam(args[1]);
                team.setPrefix(ChatColor.translateAlternateColorCodes('&', args[2]));
                sender.sendMessage("§a操作成功!");
                return true;
            }

            if (args[0].equalsIgnoreCase("suffix")) {
                Team team = teamScoreboard.getTeam(args[1]);
                team.setSuffix(ChatColor.translateAlternateColorCodes('&', args[2]));
                sender.sendMessage("§a操作成功!");
                return true;
            }
        }
        return true;
    }
