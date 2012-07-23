package com.bendude56.hunted.loadout;

import org.bukkit.inventory.ItemStack;

public class Loadout {
	private ItemStack[] contents;
	private ItemStack[] armor;

	public final String name;
	public final String directory;
	public final String filename;
	public final String fullpath;
	
	public Loadout(String name, String directory)
	{
		this.name = name;
		this.directory = directory;
		this.filename = name + ".inv";
		this.fullpath = this.directory + "/" + this.filename;
		
		load();
	}

	public Loadout(String name, String directory, ItemStack[] contents, ItemStack[] armor)
	{
		this.name = name;
		this.directory = directory;
		this.filename = name + ".inv";
		this.fullpath = this.directory + "/" + this.filename;
		
		setContents(contents, armor);
		
		save();
	}

	public void setContents(ItemStack[] contents, ItemStack[] armor)
	{
		this.contents = contents;
		this.armor = armor;
		
		save();
	}
	
	public ItemStack[] getContents()
	{
		return contents.clone();
	}
	
	public ItemStack[] getArmor()
	{
		return armor.clone();
	}
	
	public void save()
	{
		(new LoadoutFile(this)).save();
	}
	
	public void load()
	{
		(new LoadoutFile(this)).load();
	}
	
	public boolean delete() {
		return (new LoadoutFile(this)).delete();
	}
	
}
