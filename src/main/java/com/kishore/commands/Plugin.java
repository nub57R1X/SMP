package com.kishore.commands;

import com.kishore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info(Util.Chat("&l&aPlugin Enabled"));
		getLogger().info(Util.Chat("&l&bDesigned By 57R1X#7014"));
		getLogger().info("");
		getLogger().info(Util.Chat("&l&b5555555  &l&a5555555  &l&d5555555   &l&c  5   &l&e5        5"));
		getLogger().info(Util.Chat("&l&b7        &l&a     7   &l&d7     7   &l&c7 7   &l&e  7    7"));
		getLogger().info(Util.Chat("&l&bRRRRRRR  &l&a    R    &l&dRRRRRRR   &l&c  R   &l&e    RR"));
		getLogger().info(Util.Chat("&l&b      1  &l&a   1     &l&d1  1       &l&c 1   &l&e  1    1"));
		getLogger().info(Util.Chat("&l&bXXXXXXX  &l&a  X      &l&dX    X     &l&c X   &l&eX        X"));
		getLogger().info("");
		saveDefaultConfig();
//		Main main = new Main(this);
//		try {
//			main.start();
//		} catch (LoginException e) {
//			e.printStackTrace();
//		}
//		Register register = new Register(this);
//		new Login(this,register);
		new DCLock(this);
		Revamp_Register reg = new Revamp_Register(this);
		new Revamp_Login(this,reg);
//		new Death(this);
		new mcReset(this);
		new Hearts(this);
//		new AFK(this,register);
//		new Logger(this);
		new Death(this);
//		new Lock(this);
//		new SpawnKilling(this);
		Recipes recipe = new Recipes(this);
		Bukkit.addRecipe(recipe.custom_book());
		Bukkit.addRecipe(recipe.custom_heart());
		Bukkit.addRecipe(recipe.custom_shulker());
	}
	
	@Override
	public void onDisable() {
		getLogger().info(Util.Chat("&l&cPlugin Disabled"));
		getLogger().info("");
		getLogger().info(Util.Chat("&l&b5555555  &l&a5555555  &l&d5555555   &l&c  5   &l&e5        5"));
		getLogger().info(Util.Chat("&l&b7        &l&a     7   &l&d7     7   &l&c7 7   &l&e  7    7"));
		getLogger().info(Util.Chat("&l&bRRRRRRR  &l&a    R    &l&dRRRRRRR   &l&c  R   &l&e    RR"));
		getLogger().info(Util.Chat("&l&b      1  &l&a   1     &l&d1  1       &l&c 1   &l&e  1    1"));
		getLogger().info(Util.Chat("&l&bXXXXXXX  &l&a  X      &l&dX    X     &l&c X   &l&eX        X"));
		getLogger().info("");
	}
	
}
