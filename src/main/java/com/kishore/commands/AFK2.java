package com.kishore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class AFK2 implements CommandExecutor,Listener{
	private Plugin plugin;
	private final int cooldown = 180;
	private HashMap<String,Double> cool = new HashMap<>();
	public AFK2(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
//		plugin.getCommand("afk").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			System.out.println("This command Can only be run by a Player");
			return true;
		}
		Player p = (Player) sender;
		if(p.hasPotionEffect(PotionEffectType.SLOW) && p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && p.hasPotionEffect(PotionEffectType.JUMP)) {
			if(cool.containsKey(p.getName())) {
				p.sendMessage("You are not AFK");
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				p.removePotionEffect(PotionEffectType.JUMP);
			}
		}
		else {
			if(!(cool.containsKey(p.getName()))) {
				p.sendMessage("You are AFK");
				double time = System.currentTimeMillis()/1000;
				cool.put(p.getName(), time);
				return true;
			}
			else {
				double lasttime = cool.get(p.getName());
				double time = System.currentTimeMillis()/1000;
				if((time - lasttime) >= cooldown) {
					p.sendMessage("You are AFK");
					cool.put(p.getName(), time);
					return true;
				}
				else {
					p.sendMessage("Pls wait for " + (time - lasttime) + " More Seconds");
				}
			}
		}
		return false;
	}
	
	
}
