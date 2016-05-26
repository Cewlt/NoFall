package colt.nofalldamage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFall extends JavaPlugin implements Listener {

	List<String> worlds = new ArrayList<String>();

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();

		for (String world : getConfig().getStringList("worlds")) {
			if (Bukkit.getWorld(world) == null) {
				getLogger().info("Invalid world in config: " + world);
			} else {
				worlds.add(world);
			}
		}
	}

	@EventHandler
	public void entityDamageEvent(EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if (event.getCause() == DamageCause.FALL
					&& player.hasPermission("nofall.use")) {
				if (worlds.contains(player.getWorld().getName())) {
					event.setCancelled(true);
				}
			}
		}
	}
}
