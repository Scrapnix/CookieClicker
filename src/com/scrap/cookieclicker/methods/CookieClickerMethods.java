package com.scrap.cookieclicker.methods;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.scrap.cookieclicker.main.Main;
import com.scrap.cookieclicker.managers.FileManager;
import com.scrap.cookieclicker.schedulers.GameScheduler;

public class CookieClickerMethods {

	public static Main plugin;

	public CookieClickerMethods(Main instance) {
		plugin = instance;
	}
	
	public static HashMap<Player, Integer> state = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> clicks = new HashMap<Player, Integer>();
	
	public static void startCookieCountdown(Player p) {
		GameScheduler task = new GameScheduler();
		task.setP(p);
		task.setId(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 0, 20));
	}
	
	public static void startCookieClicker(Player p) {
		p.setVelocity(new Vector(0,1.5,0));
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				Location block1loc = returnedBlocksLocations1(p);
				Location block2loc = returnedBlocksLocations2(p);
				Location block3loc = returnedBlocksLocations3(p);
				Location block4loc = returnedBlocksLocations4(p);
				Location block5loc = returnedBlocksLocations5(p);
				Location block6loc = returnedBlocksLocations6(p);
				Location block7loc = returnedBlocksLocations7(p);
				
				block1loc.getWorld().getBlockAt(block1loc).setType(Material.DIAMOND_BLOCK);
				block2loc.getWorld().getBlockAt(block2loc).setType(Material.STONE);	
				block3loc.getWorld().getBlockAt(block3loc).setType(Material.BARRIER);
				block4loc.getWorld().getBlockAt(block4loc).setType(Material.BARRIER);
				block5loc.getWorld().getBlockAt(block5loc).setType(Material.BARRIER);
				block6loc.getWorld().getBlockAt(block6loc).setType(Material.BARRIER);
				block7loc.getWorld().getBlockAt(block7loc).setType(Material.BARRIER);
				
				Location loc = new Location(returnedBlocksLocations2(p).getWorld(), returnedBlocksLocations2(p).getX(), returnedBlocksLocations2(p).getY() + 0.7, returnedBlocksLocations2(p).getZ());
				p.teleport(loc);
			}

		}, 20L);
	}
	
	public static void returnPlayerResult(Player p) {
		FileManager fm = new FileManager();
		FileConfiguration saves = new FileManager().getSavesFileConfiguration();
		if (fm.getSavesFileConfiguration().getString(p.getName()) != null) {
			if (fm.getSavesFileConfiguration().getInt(p.getName()) < CookieClickerMethods.clicks.get(p)) {
				saves.set(p.getName(), CookieClickerMethods.clicks.get(p));
				try {
					saves.save(fm.getSavesFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(fm.prefix + "§2Neuer Highscore! §7Dein Score ist §e" + (CookieClickerMethods.clicks.get(p)) + "§7!");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			}else {
				p.sendMessage(fm.prefix + "§7Dein Score ist §e" + (CookieClickerMethods.clicks.get(p)) + "§7! Dein §2Highscore §7ist §e" + fm.getSavesFileConfiguration().getInt(p.getName()) + "§7!");
				p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
			}
			p.getWorld().getBlockAt(returnedBlocksLocations1(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations2(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations3(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations4(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations5(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations6(p)).setType(Material.AIR);
			p.getWorld().getBlockAt(returnedBlocksLocations7(p)).setType(Material.AIR);
			CookieClickerMethods.clicks.remove(p);
		}else {
			saves.set(p.getName(), 0);
			try {
				saves.save(fm.getSavesFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Location returnedBlocksLocations1(Player p) {
		Location eyeloc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ() + 2);
		return eyeloc;
	}
	
	public static Location returnedBlocksLocations2(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ());
		return loc;
	}
	
	public static Location returnedBlocksLocations3(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX() + 1, p.getLocation().getY(), p.getLocation().getZ());
		return loc;
	}
	
	public static Location returnedBlocksLocations4(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX() - 1, p.getLocation().getY(), p.getLocation().getZ());
		return loc;
	}
	
	public static Location returnedBlocksLocations5(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() + 1);
		return loc;
	}
	
	public static Location returnedBlocksLocations6(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() - 1);
		return loc;
	}
	
	public static Location returnedBlocksLocations7(Player p) {
		Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ());
		return loc;
	}
	
}
