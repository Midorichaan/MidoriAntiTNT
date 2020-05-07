package azisaba;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Count implements CommandExecutor {
	
	String prefix = "&aCount:&r ".replace("&", "§");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(args.length == 1) {
			if(!StringUtils.isNumeric(args[0])) {
				p.sendMessage(prefix + "§c数字を指定して下さい。");
			    return true;
			}
			
			final int count = Integer.valueOf(args[0]);
			if(count <= 0) {
				p.sendMessage(prefix + "§c1以上の数字を指定して下さい。");
			    return true;
			}
			
			if(count >= 301) {
				p.sendMessage(prefix + "§c301以上の数字は指定できません。");
			}
			
			p.sendMessage(prefix + "§aカウントダウンを開始します。");
		    p.sendMessage("§a設定:" + " " + count);
		    BukkitRunnable task = new BukkitRunnable() {
		    	int i = count;
		    	
		    	public void run() {
		    		if(i == 0) {
		    			 p.sendMessage(prefix + "§aカウントダウンが終了しました。");
		    			 Player all = (Player) Bukkit.getOnlinePlayers();
		    			 all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
		    			 cancel();
		    			 return;
		    		}
		    		Bukkit.broadcastMessage(prefix + i);
		    		Player all = (Player) Bukkit.getOnlinePlayers();
		    		all.playSound(all.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 1);
		    	    i--;
		    	}
		    };
		    task.runTaskTimer(Main.getPlugin(), 0L, 20L);
		    return true;
		}
		p.sendMessage(prefix + "§a使用法: /count <秒数>");
		return true;
	}

}
