package com.bendude56.hunted.teams;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.bendude56.hunted.loadouts.Loadout;
import com.bendude56.hunted.loadouts.LoadoutUtil;

public class PlayerState
{
	private final PlayerStateType type;
	
	private final String name;
	private final GameMode gamemode;
	private final Loadout loadout;
	private final Collection<PotionEffect> effects;
	private final Integer food;
	private final Integer health;
	private final Integer air;
	private final Integer fire;
	private final Float falldistance;
	private final Float saturation;
	private final Boolean flight;
	private final Location bed;
	private final Location compass;
	private final Float exhaustion;
	private final Integer level;
	private final Float exp;
	//private final Location location;
	

	public PlayerState(Player p, PlayerStateType type)
	{
		this.type = type;
		
		this.name = p.getName();
		this.gamemode = p.getGameMode();
		this.loadout = new Loadout(p.getInventory().getContents(), p.getInventory().getArmorContents());
		this.effects = p.getActivePotionEffects();
		this.food = p.getFoodLevel();
		this.health = p.getHealth();
		this.air = p.getRemainingAir();
		this.fire = p.getFireTicks();
		this.falldistance = p.getFallDistance();
		this.saturation = p.getSaturation();
		this.flight = p.getAllowFlight();
		this.bed = p.getBedSpawnLocation();
		this.compass = p.getCompassTarget();
		this.exhaustion = p.getExhaustion();
		this.level = p.getLevel();
		this.exp = p.getExp();
		//this.location = p.getLocation();
	}
	
	public void restorePlayer(Player p)
	{
		p.setGameMode(gamemode);
		LoadoutUtil.setPlayerInventory(p, loadout);
		p.addPotionEffects(effects);
		p.setFoodLevel(food);
		p.setHealth(health);
		p.setRemainingAir(air);
		p.setFireTicks(fire);
		p.setFallDistance(falldistance);
		p.setSaturation(saturation);
		p.setAllowFlight(flight);
		if (bed != null) p.setBedSpawnLocation(bed);
		p.setCompassTarget(compass);
		p.setExhaustion(exhaustion);
		p.setLevel(level);
		p.setExp(exp);
		//p.teleport(location);
	}
	
	public void restorePlayer()
	{
		Player p = Bukkit.getPlayer(name);
		if (p != null)
		{
			restorePlayer(p);
		}
	}
	
	public void clearInventory()
	{
		loadout.setContents(new ItemStack[36], new ItemStack[4]);
	}
	
	public String getName()
	{
		return name;
	}

	public PlayerStateType getType()
	{
		return type;
	}

	public enum PlayerStateType
	{
		ORIGINAL, MANHUNT;
	}

}
