package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Revamp_Register implements CommandExecutor,Listener{
	private Plugin plugin;
	private Statement state;
	private ResultSet result;
	private Connection conn;
    private Location l;
	public Revamp_Register(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		plugin.getCommand("register").setExecutor(this);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			state = conn.createStatement();
			result = null;
			plugin.getLogger().info(Util.Chat("&l&aConnected to the Register Servers"));
		}
		catch(Exception error) {
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to The Register Servers"));
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			System.out.println("This command can only be run by a Player");
			return true;
		}
		Player p = (Player) sender;
		try {
			result = state.executeQuery("select * from register where ign = '" + p.getName() + "'");
			if(!(result.next())) {
				//register code
				if(args.length !=2) {
					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lThis Command Needs 2 Entries with it"));
					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&l/register <password> <confirm password>"));
					return true;
				}
				String pass1 = args[0];
				String pass2 = args[1];
				if(!(pass1.equals(pass2))) {
					p.sendMessage("Passwords Dont Match Try again");
					return true;
				}
				else {
					//check if player has hearts rolled
					try {
						int RES = state.executeUpdate("insert into register values('" + p.getName() + "', '" + p.getAddress().getHostString() + "', '" + pass1 + "')");
						result = state.executeQuery("select * from hearts where ign = '" + p.getName() + "'");
						if(!(result.next())) {
							int result = state.executeUpdate("insert into hearts values('" + p.getName() + "')");
							generate(p);
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,3600 , 5));
						}
						else {

						}
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
						p.removePotionEffect(PotionEffectType.JUMP);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						p.setInvulnerable(false);
						p.setInvisible(false);
                        p.teleport(l);
						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aWELCOME TO"),Util.Chat("&b57R1X SMP"));
					}
					catch(Exception error) {
						
					}
				}
				
			}
			else {
				p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lYou Are Already Registered"));
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
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		try {
			result = state.executeQuery("select * from register where ign = '" + e.getPlayer().getName() + "'");
			if(!(result.next())) {
				Player p = e.getPlayer();
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 5));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1200, 5));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -20));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 5000));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 10));
				p.setInvisible(true);
                l = p.getLocation();
                Random rand = new Random();
                double x = rand.nextInt(5000);
                double y = 150;
                double z = rand.nextInt(5000);
                p.teleport(new Location(Bukkit.getWorld("world"),x,y,z));
				TitleAPI.sendTitle(p,20,100,20,Util.Chat("&aTO REGISTER TYPE"),"/register <pass> <confirm_pass>");
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					
					@Override
					public void run() {
						try {
							result = state.executeQuery("select * from register where ign = '" + p.getName() + "'");
							if(!(result.next())) {
								p.kickPlayer(Util.Chat("&l&cRegister Time Out"));
							}
						}
						catch(Exception error) {
							
						}
						
					}
				},600);
			}
			else {
				Player p = e.getPlayer();
				String ip = p.getAddress().getHostString();
				String check_ip = result.getString("ip");
				if(ip.equals(check_ip)) {
					TitleAPI.sendTitle(p,20,60,20,Util.Chat("&bAUTOMATIC VERIFICATION"),Util.Chat("&aSUCCESSFUL"));
				}
				else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1200, 5));
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -20));
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 100));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 5000));
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 100));
                    l = p.getLocation();
                    Random rand = new Random();
                    double x = rand.nextInt(5000);
                    double y = 150;
                    double z = rand.nextInt(5000);
                    p.teleport(new Location(Bukkit.getWorld("world"),x,y,z));
					TitleAPI.sendTitle(p,20,100,20,Util.Chat("&aTO LOGIN TYPE"),"/login <pass>");
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							try {
								result = state.executeQuery("select * from register where ign = '" + p.getName() + "'");
								if(!(result.getString("ip").equals(p.getAddress().getHostString()))) {
									p.kickPlayer(Util.Chat("&l&cLogin Time Out"));
								}
							}
							catch(Exception error) {
								
							}
						}
					},1200);
				}
			}
		}
		catch(Exception error) {
			
		}
		try {
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	public void generate(Player p) {
		Random rand = new Random();
		int randnum = rand.nextInt(15 - 7 + 1) + 7;
		TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aGENERATING RANDOM HEARTS"),Util.Chat("&bPLEASE WAIT"));
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU WILL PLAY WITH"),Util.Chat("&c" + randnum + " HEARTS"));
			}
		},100L);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				p.setMaxHealth(randnum*2);
				p.setHealth(randnum*2);
			}
		},140L);
		
	}


	@EventHandler
	public void disablecommandreload(PlayerCommandPreprocessEvent e){
		if(e.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS) && e.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
			String cmd_get = e.getMessage();
			String [] split = cmd_get.split(" ");
			String cmd = split[0].toLowerCase();
			if(cmd.contains("register")) {
				
			}
			else if(cmd.contains("login")) {
				
			}
			else {
				e.getPlayer().sendMessage(Util.Chat("| &b&l57R1X SMP &r| &l&cYou cannot Run this command Right Now"));
				e.setCancelled(true);
			}
		}
    }
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		try {
			result = state.executeQuery("select * from register where ign = '" + e.getPlayer().getName() + "'");
			Player p = e.getPlayer();
			if(!(result.next())) {
				p.teleport(l);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				p.removePotionEffect(PotionEffectType.JUMP);
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			}
			else {
				if(!(result.getString("ip").equals(p.getAddress().getHostString()))) {
					p.teleport(l);
					p.removePotionEffect(PotionEffectType.BLINDNESS);
					p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					p.removePotionEffect(PotionEffectType.JUMP);
					p.removePotionEffect(PotionEffectType.SLOW);
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				}
			}
		}
		catch(Exception error) {
			
		}
		try {
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	@EventHandler
	public void handleItemDrop(PlayerDropItemEvent e) {
		if(e.getPlayer().hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY) && e.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS)) {
			e.setCancelled(true);
		}
	}

    @EventHandler
    public void consume(PlayerItemConsumeEvent e) {
        if(e.getPlayer().hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY) && e.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if(e.getPlayer().hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY) && e.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void invopen(InventoryClickEvent e){
        if(e.getWhoClicked().hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && e.getWhoClicked().hasPotionEffect(PotionEffectType.INVISIBILITY) && e.getWhoClicked().hasPotionEffect(PotionEffectType.BLINDNESS)) {
            e.setCancelled(true);
        }
    }

    public Location getL() {
        return l;
    }
}
