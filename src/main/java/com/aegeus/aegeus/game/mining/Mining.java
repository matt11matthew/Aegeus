package com.aegeus.aegeus.game.mining;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.NBTTagInt;

public class Mining implements Listener	{
	
	private JavaPlugin parent;
	
	public Mining(JavaPlugin parent) {
		this.parent = parent;
	}
	
	/*
	 * These are the values of exp that ores will give you on your pickaxe.
	 * Also includes the range of each ore so you don't get the same xp every time. 
	 * The respawn timers are in ticks, 20 ticks in 1 second.
	 */
	private final int RUTILE_EXP = 25, RUTILE_RANGE = 10, RUTILE_TIMER = 100; //Coal Ore
	private final int BAUXITE_EXP = 50, BAUXITE_RANGE = 10, BAUXITE_TIMER = 100; //Redstone Ore
	private final int IRON_EXP = 100, IRON_RANGE = 15, IRON_TIMER = 100; // Iron Ore
	private final int LAZURITE_EXP = 150, LAZURITE_RANGE = 25, LAZURITE_TIMER = 100; // Lapis ore
	private final int CRYSTAL_EXP = 300, CRYSTAL_RANGE = 35, CRYSTAL_TIMER = 100; //Diamond Ore
	private final int EMERALD_EXP = 500, EMERALD_RANGE = 45, EMERALD_TIMER = 100; //Emerald Ore
	private final int GOLD_EXP = 1000, GOLD_RANGE = 50, GOLD_TIMER = 100; //Gold Ore
	
	public static ItemStack generatePickaxe(int level)	{
		if(level < 1 || level > 99)	{
			return null;
		}
		ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(pickaxe);
		NBTTagCompound nbt = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagCompound pick = new NBTTagCompound();
		pick.set("level", new NBTTagInt(level));
		pick.set("xp", new NBTTagInt(0));
		pick.set("xpreq", new NBTTagInt(getXPNeeded(level)));
		nbt.set("pickaxe", pick);
		nmsStack.setTag(nbt);
		pickaxe = CraftItemStack.asBukkitCopy(nmsStack);
		ItemMeta meta = pickaxe.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "Level: " + ChatColor.AQUA + level);
		lore.add(ChatColor.GRAY + "EXP: 0 / " + getXPNeeded(level));
		meta.setLore(lore);
		meta.setDisplayName("Reinforced Steel Electric Drill");
		pickaxe.setItemMeta(meta);
		return pickaxe;
	}
	
	public static int getXPNeeded(int target)	{
		if(target < 1 || target > 100)	return -1;
		return (int) (Math.pow(1.114, target) * 100);
	}
}
