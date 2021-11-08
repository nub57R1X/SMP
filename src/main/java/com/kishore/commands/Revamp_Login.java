package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;
import java.util.Random;

public class Revamp_Login implements CommandExecutor,Listener {
	private Plugin plugin;
	private Connection conn;
	private Statement state;
	private ResultSet result;
	private Revamp_Register reg;
	public Revamp_Login(Plugin plugin,Revamp_Register reg) {
		this.plugin = plugin;
		this.reg = reg;
		plugin.getCommand("login").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			state = conn.createStatement();
			result = null;
			plugin.getLogger().info(Util.Chat("&l&aConnected to the Login Servers"));
		}
		catch(Exception error) {
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to the Lock Servers"));
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			System.out.println("Only A Player can run this command");
			return true;
		}
		Player p = (Player) sender;
		try {
			result = state.executeQuery("select * from register where ign = '" +p.getName() + "'");
			if(!(result.next())) {
				p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lYou have to Register First"));
				return true;
			}
			else {
				String ip = result.getString("ip");
				String plr_ip = p.getAddress().getHostString();
				if(plr_ip.equals(ip)){
					p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lYou are Already Logged in"));
					return true;
				}
				else {
					//player login code
					if(args.length != 1) {
						p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lPlease Enter Only The Password"));
						return true;
					}
					String pass = args[0];
					String check_pass = result.getString("pass");
					if(pass.equals(check_pass)) {
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								p.teleport(reg.getL());
								TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aSUCCESSFULLY"),Util.Chat("&bLOGGED IN"));
								p.removePotionEffect(PotionEffectType.BLINDNESS);
								p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								p.removePotionEffect(PotionEffectType.JUMP);
								p.removePotionEffect(PotionEffectType.SLOW);
								p.removePotionEffect(PotionEffectType.INVISIBILITY);
								p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
							}
						},20L);
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								try {
									int result = state.executeUpdate("update register set ip = '" + plr_ip + "' where ign = '" + p.getName() + "'");
								} catch (SQLException e) {
									e.printStackTrace();
								}
								TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aWELCOME BACK"),Util.Chat("&b" + p.getName()));
							}
						},60L);
						
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								try {
									result = state.executeQuery("select * from outcheck where ign = '" + p.getName() + "'");
									if(result.next()) {
										String check2 = result.getString("outsmp");
										if(check2.equals("false")) {
											TitleAPI.sendTitle(p, 20, 60, 20, Util.Chat("&aYOU HAVE BEEN RESURRECTED"));
											plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
												
												@Override
												public void run() {
													generate(p);
												}
											},100L);
										}
									}
								}
								catch(SQLException error) {
									
								}
							}
						},150L);
					}
					else {
						p.kickPlayer(Util.Chat("&l&cWrong Password"));
					}
				}
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
	
	public void generate(Player p)  {
		Random rand = new Random();
		int randnum = rand.nextInt(15 - 5 + 1) + 5;
		TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aGENERATING RANDOM HEARTS"),Util.Chat("&bPLEASE WAIT"));
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU WILL PLAY WITH"),Util.Chat("&c" + randnum + " HEARTS"));
			}
		},100L);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				try {
					int result = state.executeUpdate("delete from outcheck where ign = '" + p.getName() + "'");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				p.setMaxHealth(randnum*2);
				p.setHealth(randnum*2);
			}
		},220L);
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
