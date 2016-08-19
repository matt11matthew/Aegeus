package aegeus.com.aegeus.obj;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AegeusItem {
	
	protected Material material;
	protected String name = "";
	protected List<String> lore = new ArrayList<>();
	
	public AegeusItem(Material material){
		this.material = material;
	}
	
	public AegeusItem(ItemStack item){
		this.material = item.getType();
		if(item.getItemMeta().getDisplayName()!=null) this.name = item.getItemMeta().getDisplayName();
		if(item.getItemMeta().getLore()!=null) this.lore = item.getItemMeta().getLore();
	}
	
	public void setMaterial(Material material){
		this.material = material;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setLore(List<String> lore){
		this.lore = lore;
	}
	
	public void addLore(String line){
		lore.add(line);
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
