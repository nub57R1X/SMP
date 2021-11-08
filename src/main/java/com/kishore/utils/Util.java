package com.kishore.utils;

import com.kishore.commands.Plugin;
import org.bukkit.ChatColor;

public class Util {
	
	private static Plugin plugin;
	public Util(Plugin plguin) {
		this.plugin= plguin;	
	}
	
	
	public static String Chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
