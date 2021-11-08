package com.kishore.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.*;
import java.util.ArrayList;


public class Recipes implements Listener,CommandExecutor{
	private Plugin plugin;
	private Player r;
	private Statement state;
	private ResultSet result;
	private Connection conn;
	private String check;
	private ItemStack itm;
	
	public Recipes(Plugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("resurrect").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
		try {
			String ip = "";
			String username = "";
			String pass = "";
			conn = DriverManager.getConnection(ip,username,pass);
			plugin.getLogger().info(Util.Chat("&l&aConnected to The Resurrecting Servers"));
			state = conn.createStatement();
		} catch (SQLException e) {	
			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to The Resurrecting Servers"));
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			plugin.getServer().getLogger().info(Util.Chat("&l&cThis command can only be run by a Player"));
			return true;
		}
		
		Player p = (Player) sender;
		try {
			itm = p.getInventory().getItemInMainHand();
			check = itm.getItemMeta().getLocalizedName();
		}
		catch(Exception error) {
			p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lPls Hold Something First"));
			return false;
		}
		
		if(check.equals("revive")) {
			String name = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
			
			if(name.equals(Util.Chat("&6Book of Resurrection"))) {
				p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lPls Rename This Book to The Person That You want to Resurrect to Use This Book"));
			}
			else {
				try {
					result = state.executeQuery("select * from outcheck where ign = '" + name + "'");
					if(!(result.next())) {
						p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lThis Player Doesnt Exist or Is not Dead Yet"));
					}
					else {
						String check2 = result.getString("outsmp");
						if(check2.equals("true")) {
							p.getInventory().removeItem(p.getInventory().getItemInMainHand());
							TitleAPI.sendTitle(p, 20, 60, 20, Util.Chat("&cRESURRECTING " + name));
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								
								@Override
								public void run() {
									try {
										int re = 0;
										re = state.executeUpdate("update outcheck set outsmp = 'false' where ign = '" + name + "'");
										if(re == 0) {
											p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lThere is Something wrong. Please Contact 57R1X with a Screenshot of the message"));
										}
										
									}
									catch(NullPointerException error) {
										p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lThere is Something wrong. Please Contact 57R1X with a Screenshot of the message"));
									} catch (SQLException e) {
										e.printStackTrace();
									}											
								}
							},120L);
							
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								
								@Override
								public void run() {
									TitleAPI.sendTitle(p, 20, 30, 20, Util.Chat("&c" + name + " HAS BEEN RESURRECTED"),Util.Chat("&cFROM THE AFTERLIFE"));
									Bukkit.broadcastMessage(Util.Chat(" | &b&lResurrect &r| &a&l" + name + " Has been Resurrected by " + p.getName()));
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
								}
							},160L);
						
						}
						else {
							p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lThis Player Doesnt Exist or Is not Dead Yet"));
						}
					}
				}
				catch(Exception error) {
					
				}
			}
			
			
		}
		else {
			p.sendMessage(Util.Chat(" | &b&lResurrect &r| &c&lPlease Hold a Book of Resurrection to Use This Command"));
		}
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public ShapedRecipe custom_book() {
		
		ItemStack r = new ItemStack(Material.ENCHANTED_BOOK);
		r.addUnsafeEnchantment(Enchantment.MENDING, 1);
		
		ItemMeta metar = r.getItemMeta();
		
		metar.setDisplayName(Util.Chat("&6Book of Resurrection"));
		metar.setLocalizedName("revive");
		ArrayList<String> lore = new ArrayList<>();
		lore.add(Util.Chat("&dA Mysterious Book Used to Resurrect Dead Beings Back to Life"));
		metar.setLore(lore);
		metar.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		r.setItemMeta(metar);
		
		ShapedRecipe recipe_r = new ShapedRecipe(new NamespacedKey(plugin, "book_of_resurrection"),r);
		
		recipe_r.shape("nnn","nbg","nnn");
		recipe_r.setIngredient('b', Material.BOOK);
		recipe_r.setIngredient('g', Material.NETHER_STAR);
		recipe_r.setIngredient('n', Material.NETHERITE_INGOT);
//		System.out.println(r.getItemMeta().getLocalizedName());
		return recipe_r;
	}
	
	public ShapedRecipe custom_heart() {
		
		ItemStack r = new ItemStack(Material.GOLDEN_APPLE);
		r.addUnsafeEnchantment(Enchantment.MENDING, 1);
		
		ItemMeta metar = r.getItemMeta();
		
		metar.setDisplayName(Util.Chat("&6FUKUROKUJU's Apple "));
		metar.setLocalizedName("heartapple");
		ArrayList<String> lore = new ArrayList<>();
		lore.add(Util.Chat("&dA Special Apple That Gives You One Extra Heart"));
		metar.setLore(lore);
		metar.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		r.setItemMeta(metar);
		
		ShapedRecipe recipe_r = new ShapedRecipe(new NamespacedKey(plugin, "heart_apple"),r);
		
		recipe_r.shape("nnn","dgd");
		recipe_r.setIngredient('g', Material.GOLDEN_APPLE);
		recipe_r.setIngredient('d', Material.DIAMOND_BLOCK);
		recipe_r.setIngredient('n', Material.NETHERITE_INGOT);
		return recipe_r;
	}
	
	public ShapedRecipe custom_shulker() {
		
		ItemStack r = new ItemStack(Material.SHULKER_BOX);		
		ItemMeta metar = r.getItemMeta();
		
		ShapedRecipe recipe_r = new ShapedRecipe(new NamespacedKey(plugin, "shulker_custom"),r);
		
		recipe_r.shape("nnn","ngn","nnn");
		recipe_r.setIngredient('g', Material.CHEST);
		recipe_r.setIngredient('n', Material.GOLD_BLOCK);
		return recipe_r;
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
//		System.out.println("eat before boi");
		ItemStack consumed = e.getItem();
		if(consumed.getItemMeta().getLocalizedName().equals("heartapple")) {
//			System.out.println("eat boi");
			Player p = e.getPlayer();
			p.setMaxHealth(p.getMaxHealth() + 2);
			p.setHealth(p.getHealth() + 2);
			ItemStack itm = p.getInventory().getItemInMainHand();
			int amt = itm.getAmount()-1;
			itm.setAmount(amt);
			p.getInventory().setItemInMainHand(itm);
			e.setCancelled(true);	
		}
	}
	

	

}
