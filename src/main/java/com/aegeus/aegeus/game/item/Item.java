package com.aegeus.aegeus.game.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aegeus.aegeus.util.Helper;

import net.minecraft.server.v1_10_R1.NBTTagCompound;

public class Item {
	
	protected Material material;
	protected String name = "";
	protected List<String> lore = new ArrayList<>();
	
	public Item(Material material){
		this.material = material;
	}
	
	public Item(ItemStack item){
		setMaterial(item.getType());
		if(item.getItemMeta().getDisplayName()!=null) setName(item.getItemMeta().getDisplayName());
		if(item.getItemMeta().getLore()!=null) setLore(item.getItemMeta().getLore());
	}
	
	public void setMaterial(Material material){
		this.material = material;
	}
	
	public void setName(String name){
		this.name = Helper.colorCodes(name);
	}
	
	public void setLore(List<String> lore){
		this.lore = lore;
	}
	
	public void addLore(String line){
		lore.add(Helper.colorCodes(line));
	}
	
	public static ItemType getItemType(ItemStack item){
		NBTTagCompound aegeus = getAegeusInfo(item);
		if(aegeus != null) return ItemType.fromTypeID(aegeus.getInt("type"));
		return null;
	}
	
	public static NBTTagCompound getAegeusInfo(ItemStack item){
		net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound;
		if(nmsStack.hasTag()){
			compound = nmsStack.getTag();
			if(compound.hasKey("AegeusInfo")) return compound.getCompound("AegeusInfo");
		}
		return null;
	}
	
	public ItemStack build(){
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		if(name!="") meta.setDisplayName(name);
		if(!lore.isEmpty()) meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(meta);
		return item;
	}
}
