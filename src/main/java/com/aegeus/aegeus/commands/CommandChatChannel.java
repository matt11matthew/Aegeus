package com.aegeus.aegeus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aegeus.aegeus.Chat;
import com.aegeus.aegeus.types.ChatChannel;
import com.aegeus.aegeus.util.Helper;

public class CommandChatChannel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		if(args.length < 1) return false;
		
		Player player = (Player) sender;
		
		if (args[0].equalsIgnoreCase("l") || args[0].equalsIgnoreCase("local")) {
			Chat.playerChatChannel.put(player, ChatChannel.LOCAL);
			sender.sendMessage(Helper.colorCodes(
					"Chat channel set to Local."));
			return true;
		} else if (args[0].equalsIgnoreCase("gl") || args[0].equalsIgnoreCase("global")) {
			Chat.playerChatChannel.put(player, ChatChannel.GLOBAL);
			sender.sendMessage(Helper.colorCodes(
					"Chat channel set to Global."));
			return true;
		} else {
			return false;
		}
	}

}
