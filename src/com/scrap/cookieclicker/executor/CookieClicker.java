package com.scrap.cookieclicker.executor;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import com.scrap.cookieclicker.managers.FileManager;
import com.scrap.cookieclicker.methods.CookieClickerMethods;

public class CookieClicker extends BukkitCommand{
	
	public CookieClicker(String name) {
		super(name);
		this.usageMessage = "/cookieclicker start/stats";
		this.description = "Der einfache CookieClicker Command!";
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		FileManager fm = new FileManager();
		if (sender instanceof Player) {
			Player p = (Player)sender;
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("start")) {
						if (!CookieClickerMethods.state.containsKey(p)) {
							CookieClickerMethods.state.put(p, 0);
						}
							if (CookieClickerMethods.state.get(p) == 0) {
								CookieClickerMethods.startCookieCountdown(p);
								CookieClickerMethods.state.put(p, 0);
								CookieClickerMethods.clicks.put(p, 0);
							}else {
								p.sendMessage(fm.prefix + "§7Du bist noch in einer CookieClicker Runde!");
							}
					}else if (args[0].equalsIgnoreCase("stats")) {
						if (args.length == 1) {
							int stats = fm.getSavesFileConfiguration().getInt(p.getName());
							p.sendMessage(fm.prefix + "§7Dein §2Highscore §7ist §e" + stats + "§7!");
						}else if (args.length == 2) {
							if (fm.getSavesFileConfiguration().getString(args[1]) != null) {
								int stats = fm.getSavesFileConfiguration().getInt(args[1]);
								p.sendMessage(fm.prefix + "§7Der §2Highscore §7vom Spieler §a" + args[1] + " §7ist §e" + stats + "§7!");
							}else {
								p.sendMessage(fm.prefix + "§cDer Spieler hat noch nie CookieClicker gespielt! Hast du dich vertippt?");
							}
						}else {
							p.sendMessage(fm.prefix + "§7Hilfe: §a/cookieclicker start/stats [name]§7!");
						}
					}else {
						p.sendMessage(fm.prefix + "§7Hilfe: §a/cookieclicker start/stats [name]§7!");
					}
				}else {
					p.sendMessage(fm.prefix + "§7Hilfe: §a/cookieclicker start/stats [name]§7!");
				}
			}else {
				
			}
		return false;
	}
	
	@SuppressWarnings("unused")
	private CommandExecutor executor = null;
	
	public void setExecutor(CommandExecutor executor) {
		this.executor = executor;
	}
	
	

}
