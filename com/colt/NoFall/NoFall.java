package com.colt.NoFall;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFall extends JavaPlugin implements Listener {
	
	List<String> worlds = getConfig().getStringList("worlds");
	
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && (p.hasPermission("nofall.use"))) {
			    for(String w : worlds) {
			    	World world = Bukkit.getWorld(w);
					if(p.getWorld().equals(world)) {
						e.setCancelled(true);
					}
			    }
			}
		}
	}
}
