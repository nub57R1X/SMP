package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import java.sql.*;


public class DCLock implements CommandExecutor,Listener{
	private Plugin plugin;
	private Connection conn;
	private Statement state;
	private ResultSet result;

	public DCLock(Plugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("dclink").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			plugin.getLogger().info(Util.Chat("&l&aConnected to the Lock Servers"));
			state = conn.createStatement();
		} catch (SQLException e) {
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to The Lock Servers"));
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			plugin.getLogger().info(Util.Chat("&l&cThis command Can only Be Run By a Player"));
			return true;
		}
		Player p = (Player) sender;
	
		if(args.length != 1) {
			p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lPlease Use The Correct Format."));
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					p.sendMessage(Util.Chat(" | &b&lLink &r| &a&l/lock <ID>"));
				}
			},20L);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lFor Example : /lock 270904126974590976"));
				}
			},40L);
			
		}
		else {
			try {
				result = state.executeQuery("select * from discord where ign = '" + p.getName() + "'");
				if(result.next()) {
					p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lYou Have Already added an Account."));
					p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lIf You want to Change the Account contact 57R1X"));
					return true;
				}
			}
			catch(Exception error) {
				
			}
			String id = args[0];
			try {
				int resultint = 0;
				resultint = state.executeUpdate("insert into discord values('" + p.getName() + "', '" + id + "')");
				if(resultint == 0) {
					p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lCouldnt Add You to Our Servers."));
					p.sendMessage(Util.Chat(" | &b&lLink &r| &c&lPlease Contact &r&b&l57R1X&r&c&l on Discord for Help"));
					return true;
				}
				else {
					TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cSUCCESSFULLY ADDED"),Util.Chat("&cYOUR ACCOUNT"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	
	@EventHandler
	public void OnLogin(PlayerLoginEvent e) throws SQLException {
		Player p = e.getPlayer();
		try {
			result = state.executeQuery("select * from mclock where ign = '" + p.getName() + "'");
			if(!(result.next())) {
				
			}
			else {
				e.disallow(Result.KICK_OTHER, Util.Chat("&c&lYour Account is Locked"));
			}
		}
		catch(Exception error) {
			
		}
		result.close();	
	}
		
}
