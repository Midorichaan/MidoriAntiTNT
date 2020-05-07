package azisaba;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Worldteleport implements CommandExecutor{
	
	String prefix = "&2[&aMidoriInv&2]&r ".replace("&", "§");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String tar = args[0];
		Player p = Bukkit.getPlayer(tar);
		
		if(p == null) {
			sender.sendMessage(prefix + "対象プレイヤーはオフラインです。");
			return false;
		}
		
		String tw = args[1];
		World w = Bukkit.getWorld(tw);
		
		if(w == null) {
			sender.sendMessage(prefix + "そのワールドは存在しません。");
			return false;
		}
		
		String x1 = args[2];
		String y1 = args[3];
		String z1 = args[4];
		String pit1 = args[5];
		String yaw1 = args[6];
		
		double x = Double.valueOf(x1);
		double y = Double.valueOf(y1);
		double z = Double.valueOf(z1);
	    float pit = Float.valueOf(pit1);
		float yaw = Float.valueOf(yaw1);
		
		if(cmd.getName().equalsIgnoreCase("mtp")){
			if(sender.hasPermission("midoriantitnt.mtp")) {
				if(args.length == 0) {
					sender.sendMessage(prefix + "Usage: /mtp <player> <world> <x y z pit yaw>");
					return false;
				}
				
				if(args.length == 7 || args.length == 6) {
					p.teleport(new Location(w, x, y, z, pit, yaw));
					p.sendMessage(prefix + "§aTeleported.");
				} else {
					sender.sendMessage(prefix + "Usage: /mtp <player> <world> <x y z pit yaw>");
					return false;
				}
				
			} else {
				sender.sendMessage(prefix + "§cYou don't have Permission.");
			}
		}
		return false;
	}

}
