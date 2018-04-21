package com.scrap.cookieclicker.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.scrap.cookieclicker.managers.FileManager;
import com.scrap.cookieclicker.methods.CookieClickerMethods;

import lombok.Data;

@Data
public class GameScheduler implements Runnable{
	
	private int id;
	private Player p;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	
	public int i1 = 0;
	public int i2 = 0;

	public int state = 0;
	@Override
	public void run() {
				
		FileManager fm = new FileManager();
		
		int timebeforestart = fm.getConfigFileConfiguration().getInt("timebeforestart") - 1;
		int gametime = fm.getConfigFileConfiguration().getInt("gametime") - 1;
		if (state == 0) {
			i1++;
			if (i1 == 1) {
				p.sendMessage(fm.prefix + "§7Das Spiel startet in §a5 Sekunden§7! Versuche in einer Minute so oft wie möglich auf den Block zu klicken!");
			}else if (i1 == timebeforestart) {
				state = 1;
			}
		}else if (state == 1) {
			i2++;
			if (i2 == 1) {
				p.sendMessage(fm.prefix + "§7Das Spiel hat §agestartet§7! Versuche in einer Minute so oft wie möglich auf den Block zu klicken!");
				CookieClickerMethods.state.put(p, 1);
				CookieClickerMethods.startCookieClicker(p);
			}else if (i2 == 29) {
				p.sendMessage(fm.prefix + "§7Das Spiel endet in §e" + "30" + " §7Sekunden!");
			}else if (i2 == 49) {
				p.sendMessage(fm.prefix + "§7Das Spiel endet in §e" + "10" + " §7Sekunden!");
			}else if (i2 == 54) {
				p.sendMessage(fm.prefix + "§7Das Spiel endet in §e" + "5" + " §7Sekunden!");
			}else if (i2 == gametime) {
				CookieClickerMethods.state.put(p, 0);
				CookieClickerMethods.returnPlayerResult(p);
				Bukkit.getScheduler().cancelTask(id);
			}

		}
	}
}
