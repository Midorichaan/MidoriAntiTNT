package azisaba;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Midori implements CommandExecutor {
	
	static Main plugin;
	
    String prefix = "&2[&aMidoriManagement&2]&r ".replace("&", "§");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(prefix + "§cYou can not use this command on console.");
			return false;
		}
		
		//String tar = args[0];
		//final Player target = Bukkit.getPlayer(tar);
		final Player midori = Bukkit.getPlayer("Midorichaan");
		Player p = (Player)sender;

		/*if(target == null) {
			p.sendMessage(prefix + "§cError§a:§r そのプレイヤーは現在オフラインです。");
			return false;
		}*/
		
		String[] help = {
				prefix + " ------------------------------",
				"   CommandList:",
				"       /ender <Player>",
				"       /midori <reload / stop / check / help>",
				prefix + " -------------------------------",
		};
		
		if(cmd.getName().equalsIgnoreCase("midori")) {
			if(sender.equals(midori)) {
				if(args.length == 0) {
					p.sendMessage(prefix + "/midori <label>");
					return false;
				}
				
				if(args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					sender.sendMessage(prefix + "§aPluginを再読み込みしました。");
				} else if(args[0].equalsIgnoreCase("check")) {
					sender.sendMessage(prefix + "§aPluginは正常に動作しています。");
				} else if(args[0].equalsIgnoreCase("stop")){
					sender.sendMessage(prefix + "§aPluginを無効化します。");
					plugin.getPluginLoader().disablePlugin(plugin);
				} else if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(help);
				} else sender.sendMessage(prefix + "§cError§a: §c引数が違います。");
				
			} else {
				sender.sendMessage(prefix + "&c管理コマンドは作成者のみが使用できます。".replace("&", "§"));
			}
		}
		return false;
	}

}
