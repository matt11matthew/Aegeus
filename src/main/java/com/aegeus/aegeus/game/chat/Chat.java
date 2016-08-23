package com.aegeus.aegeus.game.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.aegeus.aegeus.game.Statistics;
import com.aegeus.aegeus.util.Helper;

public class Chat implements Listener {

	private JavaPlugin parent;

	public Chat(JavaPlugin parent) {
		this.parent = parent;
	}
	
	public static HashMap<Player, Player> playerReply = new HashMap<>();
	public static HashMap<Player, ChatChannel> playerChatChannel = new HashMap<>();

	@EventHandler
	// Local messages and custom message format
	private void onChatEvent(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		if(Statistics.playerData.get(event.getPlayer()).isBankWithdraw)	{
			try	{
				ItemStack module = new ItemStack(Material.BOOK);
				ItemMeta meta = module.getItemMeta();
				meta.setDisplayName(ChatColor.AQUA + "Bank Note Module");
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Value: " + Integer.valueOf(event.getMessage()));
				lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "This module can be used to add gold to your bank.");
				meta.setLore(lore);
				module.setItemMeta(meta);
				event.getPlayer().getInventory().addItem(module);
				Statistics.playerData.get(event.getPlayer()).isBankWithdraw = false;
			}
			catch(Exception e)	{
				parent.getLogger().log(Level.SEVERE, "Could not parse message for bank withdrawal", e);
				Statistics.playerData.get(event.getPlayer()).isBankWithdraw = false;
			}
		}
		Player player = event.getPlayer();
		if(!Chat.playerChatChannel.containsKey(player)) Chat.playerChatChannel.put(player, ChatChannel.LOCAL);
		if (Chat.playerChatChannel.get(player).equals(ChatChannel.GLOBAL)) {
			ChatManager.sendGlobalChat(player, Helper.colorCodes(event.getMessage()));
		} else {
			ChatManager.sendLocalChat(player, Helper.colorCodes(event.getMessage()));
		}
	}
}
