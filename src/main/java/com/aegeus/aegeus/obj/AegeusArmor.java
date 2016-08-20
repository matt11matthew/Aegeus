package com.aegeus.aegeus.obj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aegeus.aegeus.types.ItemRarity;
import com.aegeus.aegeus.util.Helper;

import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.NBTTagInt;
import net.minecraft.server.v1_10_R1.NBTTagString;

public class AegeusArmor extends AegeusItem {

	Material material;
	String name;
	List<String> lore = new ArrayList<>();;
	
	int Hp = 0;
	int HpRegen = 0;
	int EnergyRegen = 0;
	
	int Tier = 0;
	int Level = 0;
	int XP = 0;
	
	ItemRarity Rarity;
	
	public AegeusArmor(Material material) {
		super(material);
	}
	
	public void setHp(int Hp){
		this.Hp = Hp;
	}
	
	public void setHpRegen(int HpRegen){
		this.HpRegen = HpRegen;
	}
	
	public void setEnergyRegen(int EnergyRegen){
		this.EnergyRegen = EnergyRegen;
	}
	
	public void setTier(int Tier){
		this.Tier = Tier;
	}
	
	public void setRarity(ItemRarity Rarity){
		this.Rarity = Rarity;
	}
	
	public static List<String> buildLore(ItemStack item){
		net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagCompound aegeus = compound.getCompound("AegeusInfo");
		int Hp = aegeus.getInt("Hp");
		int HpRegen = (aegeus.hasKey("HpRegen")) ? aegeus.getInt("HpRegen") : 0;
		int EnergyRegen = (aegeus.hasKey("EnergyRegen")) ? aegeus.getInt("EnergyRegen") : 0;
		String Rarity = (aegeus.hasKey("Rarity")) ? aegeus.getString("Rarity") : "";
		List<String> lore = new ArrayList<>();
		lore.add(Helper.colorCodes("&cHP: +" + Hp));
		if(HpRegen > 0) lore.add(Helper.colorCodes("&cHP REGEN: +" + HpRegen + "/s"));
		if(EnergyRegen > 0) lore.add(Helper.colorCodes("&cENERGY REGEN: +" + EnergyRegen + "%"));
		if(Rarity != "") lore.add(Helper.colorCodes(Rarity));
		return lore;
	}
	
	@Override
	public ItemStack build(){
		ItemStack item = new ItemStack(material);
		net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagCompound aegeus = new NBTTagCompound();
		aegeus.set("Hp", new NBTTagInt(Hp));
		aegeus.set("HpRegen", new NBTTagInt(HpRegen));
		aegeus.set("EnergyRegen", new NBTTagInt(EnergyRegen));
		aegeus.set("Tier", new NBTTagInt(Tier));
		aegeus.set("Rarity", new NBTTagString(Rarity.str));
		compound.set("AegeusInfo", aegeus);
		nmsStack.setTag(compound);
		item = CraftItemStack.asBukkitCopy(nmsStack);
		lore = buildLore(item);
		ItemMeta meta = item.getItemMeta();
		if(name!=null) meta.setDisplayName(name);
		if(lore!=null) meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(meta);
		return item;
	}
	
}
