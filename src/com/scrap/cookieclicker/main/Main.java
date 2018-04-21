package com.scrap.cookieclicker.main;

import java.lang.reflect.Field;  

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import com.scrap.cookieclicker.events.PlayerDamageListener;
import com.scrap.cookieclicker.events.PlayerInteractListener;
import com.scrap.cookieclicker.executor.CookieClicker;
import com.scrap.cookieclicker.managers.FileManager;
import com.scrap.cookieclicker.methods.CookieClickerMethods;

public class Main extends JavaPlugin{

	public CommandMap commandMap;
	
	public FileManager fm = new FileManager();
	public CookieClickerMethods ccm = new CookieClickerMethods(this);
	
	public void onEnable() {
		if (registerCommand() == true) {
			Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Alle Commands §aerfolgreich §7registriert!");
		}else {
			Bukkit.getServer().getConsoleSender().sendMessage("§6§bCookieClicker §r§7| Commands konnten §cnicht §7registriert werden! Bitte an Scrapnix wenden!");
		}
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
 		fm.setFiles();
	}
		
	public Boolean registerCommand() {
		if (Bukkit.getServer() instanceof CraftServer) {
			try {
				final Field f = CraftServer.class.getDeclaredField("commandMap");
				f.setAccessible(true);
				commandMap = (CommandMap)f.get(Bukkit.getServer());
				CookieClicker cmd = new CookieClicker("cookieclicker");
				commandMap.register("cookieclicker", cmd);
				cmd.setExecutor(this);
				return true;
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
	}
	
}
