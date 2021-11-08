package com.kishore.commands;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class Online implements Listener{
	private Plugin plugin;
	private List<String> online = new ArrayList<>();
	public Online(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);	
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		online.add(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) {
		online.remove(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if(online.contains(e.getPlayer().getName())) {
			e.disallow(Result.KICK_OTHER, "Player is Already Online on The Server");
		}
	}
}
