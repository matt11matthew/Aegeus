package com.aegeus.aegeus.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aegeus.aegeus.game.chat.ChatMethods;
import com.aegeus.aegeus.util.Helper;

public class CommandMessage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		if (args.length < 2) return false;
		
		Player player = (Player) sender;
		Player target = Bukkit.getPlayer(args[0]);
		String msg = Helper.buildArgString(args, 1);
		
		ChatMethods.sendPrivateMessage(player, target, msg.trim());
		return true;
	}

}
