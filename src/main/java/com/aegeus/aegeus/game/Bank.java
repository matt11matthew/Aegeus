package com.aegeus.aegeus.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.aegeus.aegeus.util.InventorytoBase64;

public class Bank implements Listener{
	private JavaPlugin parent;
	private Map<UUID, String> data = new HashMap<>(); //Map containing bank data for the players.  UUID = Player UUID, String = Base 64 Serialized Inventory.
	
	/**
	 * Main constructor
	 * @param parent
	 */
	public Bank(JavaPlugin parent) {
		this.parent = parent;
	}
	
	@EventHandler
	public void onOpenChest(InventoryOpenEvent e)	{
		if(e.getInventory().getType() == InventoryType.ENDER_CHEST)	{
			//The player has opened a vanilla ender chest.  Let's cancel it and give them our custom ender chest with more customizability.
			e.setCancelled(true);
			Player p = (Player) e.getPlayer();
			Inventory inv = Bukkit.createInventory(p, 9, "Bank");
			ItemStack block = new ItemStack(Material.GOLD_BLOCK);
			ItemMeta blockMeta = block.getItemMeta();
			blockMeta.setDisplayName(ChatColor.GOLD + "0 Gold(Test)");
			block.setItemMeta(blockMeta);
			inv.setItem(inv.getSize() - 1, block);
			if(!data.get(p.getUniqueId()).equals(null))	{
				try {
					inv = InventorytoBase64.fromBase64(data.get(p.getUniqueId()), "Bank");
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
		if(e.getInventory().getName().equalsIgnoreCase("Bank"))	{
			//The player has closed our custom bank inventory.  Spit out the contents of the array as a string to the console.
			data.put(e.getPlayer().getUniqueId(), InventorytoBase64.toBase64(e.getInventory()));
		}
	}
	
	@EventHandler
	public void onClickEvent(InventoryClickEvent e)	{
		
	}
	
	@EventHandler
	public void onBlockInteractEvent(PlayerInteractEvent e)	{
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().isSneaking() && e.getClickedBlock().getType() == Material.ENDER_CHEST)	{
			e.getPlayer().sendMessage(ChatColor.AQUA + "You Shift-Right Clicked an Ender Chest Block!");
		}
	}
}