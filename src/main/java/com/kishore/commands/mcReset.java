package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.*;

public class mcReset implements CommandExecutor{
	private Plugin plugin;
	private Connection conn;
	private Statement state;
	private ResultSet result;
	public mcReset(Plugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("reset").setExecutor(this);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			plugin.getLogger().info(Util.Chat("&l&aConnected to the Reset Servers"));
			state = conn.createStatement();
		} catch (SQLException e) {
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to the Reset Servers"));
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(!(p.hasPermission("57r1x.reset"))) {
			p.sendMessage(Util.Chat(" | &b&lReset &r| &c&lYou dont have the permission to use this Command"));
			return true;
		}
		if(args.length == 0 || args.length > 1) {
			p.sendMessage(Util.Chat(" | &b&lReset &r| &c&lThere Should be One and Only one Value"));
			return true;
		}
		String name = args[0];
		try {
			result = state.executeQuery("select * from register where ign = '" + name + "'");
			if(!(result.next())) {
				p.sendMessage(Util.Chat(" | &b&lReset &r| &c&lThis Player is Not Registered"));
				return true;
			}
			int get = 0;
			get = state.executeUpdate("delete from register where ign = '" + name + "'");
			if(get == 0) {
				p.sendMessage(Util.Chat(" | &b&lReset &r| &c&lReset Failed Please Contact 57R1X on discord for more Info"));
				return true;
			}
			p.sendMessage(Util.Chat(" | &b&lReset &r| &a&l" + name + "'s Password has been Reset"));
			Player kick = Bukkit.getPlayer(name);
			if(kick.isOnline()) {
				TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cYOUR PASSWORD HAS BEEN RESET"),Util.Chat("&cYOU WILL BE KICKED OUT IN"));
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cIN"),Util.Chat("&b5"));
					}
				},20L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cIN"),Util.Chat("&b4"));
					}
				},40L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cIN"),Util.Chat("&a3"));
					}
				},60L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cIN"),Util.Chat("&a2"));
					}
				},80L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						TitleAPI.sendTitle(kick,20,60,20,Util.Chat("&cIN"),Util.Chat("&c1"));
					}
				},100L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					
					@Override
					public void run() {
						kick.kickPlayer(Util.Chat("&c&lYou're Password has been Reset. Please Register Again"));						
					}
				},120L);
				
				return true;
			}
			else {
				p.sendMessage(Util.Chat(" | &b&lReset &r| &c&lCouldn't Kick Player Because Player is not Online"));
				return true;
			}
			
		}
		catch(Exception error) {
			
		}
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

}
