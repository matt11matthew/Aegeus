package com.aegeus.aegeus.game.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.aegeus.aegeus.util.Helper;
import com.aegeus.aegeus.util.exceptions.NoneNearbyException;

public class ChatMethods {
	
	public static void sendAutoChat(Player player, String msg){
		String[] split = msg.split(" ");
		if(split[0].equalsIgnoreCase("WTB") || split[0].equalsIgnoreCase("WTS") || split[0].equalsIgnoreCase("WTT") ||
				split[0].equalsIgnoreCase("Buying") || split[0].equalsIgnoreCase("Selling") || split[0].equalsIgnoreCase("Trading")){
			sendTradeChat(player, msg);
		} else if (split[0].equalsIgnoreCase("WTR") || split[0].equalsIgnoreCase("Recruiting")){
			sendRecruitChat(player, msg);
		} else {
			sendGlobalChat(player, msg);
		}
	}
	
	public static void sendGlobalChat(Player player, String msg) {
		Bukkit.broadcastMessage(Helper.colorCodes(
				"&b(&lG&b)&7 " + player.getDisplayName() + ": &f" + Helper.colorCodes(msg)));
	}

	public static void sendTradeChat(Player player, String msg) {
		Bukkit.broadcastMessage(Helper.colorCodes(
				"&a(&lT&a)&7 " + player.getDisplayName() + ": &f" + msg));
	}
	
	public static void sendRecruitChat(Player player, String msg) {
		Bukkit.broadcastMessage(Helper.colorCodes(
				"&e(&lR&e)&7 " + player.getDisplayName() + ": &f" + msg));
	}
	
	public static void sendBroadcast(String msg){
		Bukkit.broadcastMessage(Helper.colorCodes(
				"&e&l»&e " + msg));
	}
	
	public static void sendLocalChat(Player player, String msg) {
		try {
			sendRadialChat(player, ChatColor.GRAY + player.getDisplayName() + ":" + ChatColor.WHITE + " " + msg);
		} catch (NoneNearbyException e){
			player.sendMessage("" + ChatColor.GRAY + ChatColor.ITALIC + "Your voice echoes in the wind.");
		}
	}
	
	public static void sendRadialChat(Player player, String msg) throws NoneNearbyException {
		player.sendMessage(msg);
		boolean peopleNearby = false;
		for (Entity nearby : player.getNearbyEntities(30, 30, 30)) {
			if(nearby.getType().equals(EntityType.PLAYER)
					&& !player.equals((Player) nearby)){
				peopleNearby = true;
				nearby.sendMessage(msg);
			}
		}
		if(!peopleNearby) throw new NoneNearbyException("No players nearby.");
	}
	
	/**
	 * Sends a private message to a player.
	 * 
	 * @param sender
	 *            Sender player.
	 * @param target
	 *            Recipent player.
	 * @param message
	 *            Message to send.
	 */
	public static void sendPrivateMessage(Player sender, Player target, String message) {
		if (target.isOnline()) {
			sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "TO: " + ChatColor.GREEN + target.getDisplayName()
					+ ChatColor.GRAY + " - " + ChatColor.WHITE + message.trim());
			target.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "FROM: " + ChatColor.GREEN
					+ sender.getDisplayName() + ChatColor.GRAY + " - " + ChatColor.WHITE + message.trim());
			target.playSound(target.getLocation(), "mob.chicken.plop", 100, 1);
			Chat.playerReply.put(target, sender);
		} else {
			sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + target.getDisplayName() + ChatColor.RED
					+ " is offline or does not exist.");
		}
	}
	
}
