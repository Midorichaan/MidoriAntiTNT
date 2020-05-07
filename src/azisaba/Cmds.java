package azisaba;

import java.sql.Timestamp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Cmds implements CommandExecutor{
	
	String prefix = "&2[&aMidoriInv&2]&r ".replace("&", "§");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(prefix + "§cYou can not use this command on console.");
			return false;
		}
		
		String tar = args[0];
		final Player target = Bukkit.getPlayer(tar);
		//final Player midori = Bukkit.getPlayer("cb66ff56f21b413394cfd834225e569d");
		Player p = (Player)sender;

		if(target == null) {
			p.sendMessage(prefix + "§cError§a:§r そのプレイヤーは現在オフラインです。");
			return false;
		}
		
		Inventory other = target.getEnderChest();
		
		if(cmd.getName().equalsIgnoreCase("ender")) {
			if(p.hasPermission("midoriantitnt.ender")) {
				if(args.length == 0) {
					p.sendMessage(prefix + "/ender <Player>");
					return false;
				}
				
				if(args.length == 1) {
					p.openInventory(other);
					p.sendMessage(prefix + target.getName() + "のエンダーチェストを開きました。");
					return false;
				} else {
					p.sendMessage(prefix + "§cError.");
					return false;
				}
				
			} else {
				p.sendMessage(prefix + "&cYou don't have Permission.".replace("&", "§"));
			}
		}
		
		Location locsender = p.getLocation();
		Location loctarget = target.getLocation();
		
		if(cmd.getName().equalsIgnoreCase("ptp")) {
			if(sender.hasPermission("midoriantitnt.ptp")) {
			
			if (args.length >= 3) {
				sender.sendMessage(prefix + "コマンドが無効化されました。");
				return true;
			}
			
			if (target.isOnline() == false) {
				sender.sendMessage(prefix + "対象プレイヤー" + " " + args[0] + " " + "はオフラインです。");
				return true;
			}
			    
			    if(args[1].equalsIgnoreCase("here")) {
			    	target.teleport(locsender);
			    	p.sendMessage(prefix + target.getName() + "を自分の元へTPさせました。");
			    } else if(args[1].equalsIgnoreCase("to")) {
			    		p.teleport(loctarget);
			    		p.sendMessage(prefix + target.getName() + "にTPしました。");
			    	} else 
			    		if(args[1].isEmpty() != false && args[1] == null) {
						sender.sendMessage(prefix + "対象を自分の場所へTPさせるにはhereを、対象へTPするにはtoを入力してください。");
						return false;
			    		}
			    	} else {
					sender.sendMessage(prefix + "§cYou don't have Permission.");
				}
		}
		
		//String tar = args[0];
		//Player target = Bukkit.getPlayer(tar);
		//Location loc = target.getLocation();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String[] str = {
				prefix + "------------------------------",
				"   Player: " + p.getName(),
				"   UUID: " + p.getUniqueId().toString(),
				"   World: " + p.getWorld().getName(),
				"   Location: " + locsender.getBlockX() + ", " + locsender.getBlockY() + ", " + locsender.getBlockZ(),
				"   Rank: " + "null",
				"   Admin: " + p.isOp(),
				"   Time: " + timestamp,
				prefix + "------------------------------",
		};
		
		if(cmd.getName().equalsIgnoreCase("status")) {
			if(sender.hasPermission("midoriantitnt.status")) {
				if(args.length == 0) {
					sender.sendMessage(str);
			} else {
				sender.sendMessage(prefix + "§cInvailed args.");
		    } 
			} else {
				sender.sendMessage(prefix + "§cYou don't have Permission.");
		        return false;
         	}
			

        }
		
		if(cmd.getName().equalsIgnoreCase("mfly")) {
			if(sender.hasPermission("midoriutil.mfly")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(prefix + "&cYou can't use this command on console.".replace("&", "§"));
					return true;
				}
				
				Player s = (Player)sender;
				String tg = args[1];
				Player tgs = Bukkit.getPlayer(tg);
				
				boolean on = true;
				boolean off = false;
				
				if(args.length == 0) {
					sender.sendMessage("§aMissing args.");
					return false;
				}
				
				if(args[0].equalsIgnoreCase("on")) {
					if(args.length == 1) {
				    	if(s.getAllowFlight() == on) {
						  sender.sendMessage("§aFlymode has already on.");
						  return false;
					    } else {
					    	s.setAllowFlight(on);
					    	s.sendMessage("§aFlymode on.");
					    }
					return false;
					} else if(args.length == 2) {
						if(tgs.getAllowFlight() == on) {
							sender.sendMessage("§aPlayer " + tgs.getName() + "'s flymode has already on.");
							return false;
						} else {
							sender.sendMessage("§aPlayer" + tgs.getName() + "'s flymode on.");
							tgs.setAllowFlight(on);
						}
						return false;
					} 
				} else if(args[0].equalsIgnoreCase("off")) {
					if(args.length == 1) {
				    	if(s.getAllowFlight() == off) {
						  sender.sendMessage("§aFlymode has already off.");
						  return false;
					    } else {
					    	s.setAllowFlight(off);
					    	s.sendMessage("§aFlymode off.");
					    }
					return false;
					} else if(args.length == 2) {
						if(tgs.getAllowFlight() == off) {
							sender.sendMessage("§aPlayer " + tgs.getName() + "'s flymode has already off.");
							return false;
						} else {
							sender.sendMessage("§aPlayer" + tgs.getName() + "'s flymode off.");
							tgs.setAllowFlight(off);
						}
						return false;
				    }
				} else sender.sendMessage("§aInvailed args.");
				
			} else {
				sender.sendMessage(prefix + "§cYou don't have Permission.");
			}
		}
		
		return false;
	}

}
