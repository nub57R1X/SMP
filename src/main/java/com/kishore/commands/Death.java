package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;

public class Death implements Listener{
	private Plugin plugin;
	private Statement state;
	private Connection conn;
	private ResultSet result;
	
	public Death(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			state = conn.createStatement();
			result = null;	
		}
		catch(SQLException error) {
		}
		
	}
	
	@EventHandler
	public void onRespawnEvent(PlayerRespawnEvent e) throws InterruptedException, SQLException {
		Player p = e.getPlayer();
		if(p.getKiller() instanceof Player) {
			Player killer = p.getKiller();
			double killerhealth = killer.getMaxHealth();
			double playerhealth = p.getMaxHealth();
			try {
				result = state.executeQuery("select outsmp from outcheck where ign = '" + p.getName() + "'");
				if(!(result.next())) {
					double hearts = p.getMaxHealth();
					if(hearts-2 == 0) {
						dead(p);
					}
					else {
						TitleAPI.sendTitle(killer,20,60,20,Util.Chat("&aYOU GOT ONE OF"),Util.Chat("&c" + p.getName() + "'s Heart"));
						p.sendMessage(Util.Chat("| &b&lInvulnerable &r| &a&lYou will be Invulnerable for The Next 1 Minute"));
						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cYOU LOST A HEART TO"),Util.Chat("&c" + killer.getName()));
						killer.setMaxHealth(killerhealth + 2);
						p.setMaxHealth(playerhealth -2);
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 5000));
							}
						},70L);
					}
				}
				else {
					String check = result.getString("outsmp");
					if(check.equals("false")) {
						p.setMaxHealth(20);
						p.setHealth(20);
					}
				}
			}
			catch(Exception error) {
				
			}
		}
		result.close();	
	}
	
	
	public void dead(Player p) throws InterruptedException, SQLException {
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 5000));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 100));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -20));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 100));
				TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cYOU LOST ALL YOUR HEARTS"),Util.Chat("&cAND DIED AS A RESULT"));				
			}
		},0L);
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cHOPE YOUR FRIENDS"),Util.Chat("&cWILL RESURRECT YOU"));				
				try {
					int res = state.executeUpdate("update register set ip = '1234' where ign = '" + p.getName() + "'");
					int result = state.executeUpdate("insert into outcheck values('" + p.getName() + "', 'true')");
				} catch (SQLException e) {
					e.printStackTrace();
				}

				p.setMaxHealth(20);
				p.setHealth(20);
				p.kickPlayer(Util.Chat("&c&lYou Are Dead\nPlease Wait for Your Friends to Resurrect You"));
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.SLOW);	
				p.removePotionEffect(PotionEffectType.JUMP);
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				Bukkit.broadcastMessage(Util.Chat(" | &b&lDeath &r| &b" + p.getName() + " Went to Get Milk When his Son was 4 Years Old and Died When a Coconut Fell on his Head"));
			}
		},120L);
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(Util.Chat(" | &b&lDeath &r| &bA Moment of Silence for " + p.getName()));
				
			}
		},200L);
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(Util.Chat(" | &b&lDeath &r| &bRIP " + p.getName() + "...."));
			}
		},240L);
		result.close();	
	}
}
