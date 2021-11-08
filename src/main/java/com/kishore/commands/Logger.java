package com.kishore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger implements Listener{
	private Plugin plugin;
	private File latest;
	
	public Logger(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		create();
		Player p = e.getPlayer();
		login_log(p);
	}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) throws IOException {
		create();
		Player p = e.getPlayer();
		leave_log(p);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) throws IOException {
		create();
		Player p = e.getEntity();
		die_log(p);
	}
	
	public void create() throws IOException {
		DateFormat format = new SimpleDateFormat("dd-MM-YYYY");
		Date date = new Date();
		String todate = format.format(date);
		String file_name = todate + ".txt";
		File file = new File("D:\\Kishore\\57R1X Log\\" + file_name);
		if(!(file.exists())) {
			file.createNewFile();
			latest = file;
		}
		else {
			latest = file;
		}
	}
	public void login_log(Player p) throws IOException {
		String ip  = p.getAddress().getHostString();
		String name = p.getName();
		
		Location l = p.getLocation();
		double x = l.getX();
		double y = l.getY();
		double z = l.getZ();
		
		DateFormat format = new SimpleDateFormat("hh:mm:ss dd-MM-YYYY");
		Date date_2 = new Date();
		String date = format.format(date_2);
		FileWriter fr = new FileWriter(latest,true);
		BufferedWriter wr = new BufferedWriter(fr);
		
		wr.write("Player Joined : ");
		wr.write("\n\tPlayer_Name : " + name);
		wr.write("\n\tIp_Address : " + ip);
		wr.write("\n\tTime Joined : " + date);
		wr.write("\n\tX Coordinate : " + x);
		wr.write("\n\tY Coordinate : " + y);
		wr.write("\n\tZ Coordinate : " + z);
		wr.write("\n\n");
		wr.close();
	}
	
	public void leave_log(Player p) throws IOException {
		String ip  = p.getAddress().getHostString();
		String name = p.getName();
		DateFormat format = new SimpleDateFormat("hh:mm:ss dd-MM-YYYY");
		
		Location l = p.getLocation();
		double x = l.getX();
		double y = l.getY();
		double z = l.getZ();
		
		Date date_2 = new Date();
		String date = format.format(date_2);
		FileWriter fr = new FileWriter(latest,true);
		BufferedWriter wr = new BufferedWriter(fr);
		wr.write("Player Left : ");
		wr.write("\n\tPlayer_Name : " + name);
		wr.write("\n\tIp_Address : " + ip);
		wr.write("\n\tTime Joined : " + date);
		wr.write("\n\tX Coordinate : " + x);
		wr.write("\n\tY Coordinate : " + y);
		wr.write("\n\tZ Coordinate : " + z);
		wr.write("\n\n");
		wr.close();
	}
	
	public void die_log(Player p) throws IOException {
		String name = p.getName();
		String ip = p.getAddress().getHostString();
		String killer = null;
		String killer_ip = null;
		try {
			killer = p.getKiller().getName();
			killer_ip = p.getKiller().getAddress().getHostString();
		}
		catch(Exception error) {
			
		}
		
		DateFormat dt2 = new SimpleDateFormat("hh:mm:ss dd-MM-YYYY");
		Date d2 = new Date();
		String date = dt2.format(d2);
		
		Location l = p.getLocation();
		double x = l.getX();
		double y = l.getY();
		double z = l.getZ();
		FileWriter fr = new FileWriter(latest,true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write("Player Died : ");
		br.write("\n\tPlayer_Name : " + name);
		br.write("\n\tPlayer_Ip_Address : " + ip);
		br.write("\n\tKiller Name : " + killer);
		br.write("\n\tKiller_Ip_Address : " + killer_ip);
		br.write("\n\tTime Died : " + date);
		br.write("\n\tX Coordinate : " + x);
		br.write("\n\tY Coordinate : " + y);
		br.write("\n\tZ Coordinate : " + z);
		br.write("\n\n");
		br.close();
	}
}
