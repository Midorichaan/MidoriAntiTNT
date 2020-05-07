package azisaba;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import azisaba.Events;

public class Main extends JavaPlugin{
	
	static Main plugin;
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public Main() {
		
	}
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		Bukkit.getPluginCommand("ender").setExecutor(new Cmds());
		Bukkit.getPluginCommand("ptp").setExecutor(new Cmds());
		Bukkit.getPluginCommand("status").setExecutor(new Cmds());
		Bukkit.getPluginCommand("mfly").setExecutor(new Cmds());
		Bukkit.getPluginCommand("mtp").setExecutor(new Worldteleport());
		plugin = this;
		Bukkit.getPluginCommand("count").setExecutor(new Count());
		Bukkit.getPluginCommand("midori").setExecutor(new Midori());
	}
	
	public void onDisable() {
		
	}

}
