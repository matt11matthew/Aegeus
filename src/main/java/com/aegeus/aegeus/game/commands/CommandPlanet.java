package com.aegeus.aegeus.game.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aegeus.aegeus.game.planets.Planet;
import com.aegeus.aegeus.game.planets.PlanetManager;
import com.aegeus.aegeus.game.planets.Planets;

public class CommandPlanet implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
		if(args.length < 1) return false;
		
		Player player = (Player) sender;
		Planet planet = null;
		String planetget = args[0];
		
		if(planetget.equalsIgnoreCase("terminal")) {
			planet = Planets.TERMINAL.getPlanet();
		} else if(planetget.equalsIgnoreCase("xylo")) {
			planet = Planets.PLANET_XYLO.getPlanet();
		} else {
			return false;
		}
		
		PlanetManager.warpPlayer(player, planet);
		return true;
	}

}
