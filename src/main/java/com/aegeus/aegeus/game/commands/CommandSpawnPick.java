package com.aegeus.aegeus.game.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aegeus.aegeus.game.mining.Mining;

public class CommandSpawnPick implements CommandExecutor	{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		Player p = (Player) sender;
		int level = 0;
		try	{
			level = Integer.valueOf(arg3[0]);
		}
		catch(Exception e)	{
			return false;
		}
		p.getInventory().addItem(Mining.generatePickaxe(level));
		return true;
	}

}
