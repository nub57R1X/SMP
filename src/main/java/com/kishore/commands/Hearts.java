package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import java.sql.*;

public class Hearts implements CommandExecutor,Listener{
	private Plugin plugin;
	private Player p2;
	private ResultSet result;
	private Connection conn;
	private Statement state;
	public Hearts(Plugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("heart").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			state = conn.createStatement();
			plugin.getLogger().info(Util.Chat("&l&aConnected to The Out Servers"));
		} catch (SQLException e) {
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to The Out Servers"));
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
		if(!(args.length == 1)) {
			p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cThere Should be One and Only One Query for this Command"));
			return true;
		}
		
		String name = args[0];

		if(p.getName().equals(name)) {
			p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cYou Cannot Send Hearts To Yourself"));
			return true;
		}
		
		try {
			p2 = Bukkit.getPlayer(name);
//			System.out.println(p2.getName());
			
			if(p2.isWhitelisted() == false) {
				p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cThis Player has not Joined The Server"));
				return true;
			}
			
			if(p2.isOnline() == false) {
				p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cThat Player is not Online"));
				return true;
			}
		}
		catch(CommandException e) {
			p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cThis Player is Not in this Server"));
			return true;
		}
		
		
		double pheart = p.getMaxHealth();
		double p2heart = p2.getMaxHealth();
		if(pheart == 2) {
			p.sendMessage(Util.Chat(" | &b&lHearts &r| &l&cYou Cannot Send Your One and Only Heart"));
			return true;
		}
		
		TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU GAVE A HEART TO "),Util.Chat("&b" + p2.getName()));
		p.setMaxHealth(pheart - 2);
		TitleAPI.sendTitle(p2,20,60,20,Util.Chat("&aYOU RECEIVED A HEART FROM"),Util.Chat("&b" + p.getName()));
		double val = p2heart + 2;
		p2.setMaxHealth(val);
		p2.setHealth(p2.getHealth() + 2);
		return false;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) throws SQLException {
		Player p = e.getPlayer();
		try {
			result = state.executeQuery("select outsmp from outcheck where ign = '" + p.getName() + "'");
			if(!(result.next())) {
				
			}
			else {
				String check = result.getString("outsmp");
				if(check.equals("true")){
					e.disallow(Result.KICK_OTHER, Util.Chat("&c&lYou Are Dead\nPlease Wait for Your Friends to Resurrect You"));
				}
				if(check.equals("false")) {
					p.setInvulnerable(false);
				}
			}
		}
		catch(Exception error){
	
		}
		result.close();	
	}
	
}
