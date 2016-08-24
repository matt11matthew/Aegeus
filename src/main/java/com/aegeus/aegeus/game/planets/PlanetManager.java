package com.aegeus.aegeus.game.planets;

import org.bukkit.entity.Player;

import com.aegeus.aegeus.game.Statistics;
import com.aegeus.aegeus.util.Helper;

public class PlanetManager {
	
	public static void warpPlayer(Player player, Planet planet){
		if(planet.getLevelRequired() > 0
				&& Statistics.playerData.get(player).level < planet.getLevelRequired()){
			player.sendMessage(Helper.colorCodes("&cYou are not a high enough level to warp to " + planet.getName() + "."));
		}
		else if(Statistics.playerData.get(player).currentPlanet.equals(planet)){
			player.sendMessage(Helper.colorCodes("&cYou are already on " + planet.getName() + "."));
		} else {
			player.sendMessage(Helper.colorCodes("&bYou are heading to " + planet.getName() + "..."));
			player.sendMessage(Helper.colorCodes("&7&o" + planet.getDescription()));
			player.teleport(planet.getLocation());
			Statistics.playerData.get(player).currentPlanet = planet;
		}
	}
	
}
