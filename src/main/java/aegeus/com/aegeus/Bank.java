package aegeus.com.aegeus;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import aegeus.com.aegeus.obj.AegeusItem;
import aegeus.com.aegeus.util.InventorytoBase64;

public class Bank implements Listener{
	private JavaPlugin p;
	private String data = "";
	
	/**
	 * Main constructor
	 * @param p
	 */
	public Bank(JavaPlugin p)	{
		this.p = p;
	}
	
	@EventHandler
	public void onOpenChest(InventoryOpenEvent e)	{
		if(e.getInventory().getType() == InventoryType.ENDER_CHEST)	{
			//The player has opened a vanilla ender chest.  Let's cancel it and give them our custom ender chest with more customizability.
			e.setCancelled(true);
			Player p = (Player) e.getPlayer();
			Inventory inv = Bukkit.createInventory(p, 9, ChatColor.GREEN + "Test inventory");
			AegeusItem block = new AegeusItem(Material.GOLD_BLOCK);
			block.setName("&eTesting 123");
			inv.setItem(8, block.build());
			if(!data.equals(""))	{
				try {
					inv = InventorytoBase64.fromBase64(data, ChatColor.GREEN + "Test inventory");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					p.getServer().getLogger().log(Level.INFO, "Could not read from string to inventory while loading bank.", e1);
				}
			}
			p.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onCloseChest(InventoryCloseEvent e)	{
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GREEN + "Test inventory"))	{
			//The player has closed our custom bank inventory.  Spit out the contents of the array as a string to the console.
			data = InventorytoBase64.toBase64(e.getInventory());
		}
	}
}