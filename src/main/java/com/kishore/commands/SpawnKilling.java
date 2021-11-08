package com.kishore.commands;

import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SpawnKilling implements Listener{
	private Plugin plugin;
	public SpawnKilling(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) throws InterruptedException {
		Player p = e.getEntity();
		p.setInvulnerable(true);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				p.sendMessage(Util.Chat("| &b&lInvulnerable &r| &a&lYou will be Invulnerable for The Next 1 Minute"));
				
			}
		},100L);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				p.sendMessage(Util.Chat("| &b&lInvulnerable &r| &a&lYour Invulnerability Wore Off"));
				p.setInvulnerable(false);
			}
		},1200L);
	}
	
}
