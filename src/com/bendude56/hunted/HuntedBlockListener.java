package com.bendude56.hunted;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class HuntedBlockListener extends BlockListener {
	
	public HuntedBlockListener() {
		Bukkit.getPluginManager().registerEvent(Event.Type.BLOCK_BREAK, this, Event.Priority.Normal, HuntedPlugin.getInstance());
		Bukkit.getPluginManager().registerEvent(Event.Type.BLOCK_PLACE, this, Event.Priority.Normal, HuntedPlugin.getInstance());
	}

	private Game g = HuntedPlugin.getInstance().getGame();
	private SettingsFile settings = HuntedPlugin.getInstance().getSettings();
	
	public void onBlockPlace(BlockPlaceEvent e) {
		if (g.gameHasBegun()) {
			if (g.isSpectating(e.getPlayer())) {
				e.setCancelled(true);
			}
			else if (g.isHunted(e.getPlayer())
					&& g.getDistance(e.getBlock().getLocation(),
					settings.hunterSpawn)
					<= settings.noBuildRange()) {
				e.setCancelled(true);
			}
			else if (g.isHunter(e.getPlayer())
					&& g.getDistance(e.getBlock().getLocation(),
					settings.preySpawn)
					<= settings.noBuildRange()) {
				e.setCancelled(true);
			}
		}
	}
	
	public void onBlockBreak(BlockBreakEvent e) {
		if (g.gameHasBegun()) {
			if (g.isSpectating(e.getPlayer())) {
				e.setCancelled(true);
			}
			else if (g.isHunted(e.getPlayer())
					&& g.getDistance(e.getBlock().getLocation(),
							settings.hunterSpawn)
							<= settings.noBuildRange()
					|| g.getDistance(e.getBlock().getLocation(),
							HuntedPlugin.getInstance().getWorld().getSpawnLocation())
							<= settings.noBuildRange()) {
				e.setCancelled(true);
			}
			else if (g.isHunter(e.getPlayer())
					&& g.getDistance(e.getBlock().getLocation(),
					settings.preySpawn)
					<= settings.noBuildRange()) {
				e.setCancelled(true);
			}
		}
	}
	
}