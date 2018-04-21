package com.scrap.cookieclicker.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.scrap.cookieclicker.methods.CookieClickerMethods;
import com.scrap.cookieclicker.methods.PlayerActionBarSender;

public class PlayerInteractListener implements Listener{
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.LEFT_CLICK_BLOCK | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (CookieClickerMethods.state.get(p) == 1) {
				if (e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)) {
					if (CookieClickerMethods.clicks.get(p) == 0) {
						CookieClickerMethods.clicks.put(p, 1);
						PlayerActionBarSender pabspacket = new PlayerActionBarSender("§6-- §a1 §6--");
						pabspacket.sendToPlayer(p);
						p.playSound(p.getLocation(), Sound.DIG_STONE, 1F, 1F);
					}else {
						PlayerActionBarSender pabspacket = new PlayerActionBarSender("§6-- §a" + (CookieClickerMethods.clicks.get(p) + 1) + " §6--");
						pabspacket.sendToPlayer(p);
						CookieClickerMethods.clicks.put(p, CookieClickerMethods.clicks.get(p) + 1);
						p.playSound(p.getLocation(), Sound.STEP_STONE, 1F, 1F);
					}
				}
			}
		}
	}

}
