package com.aegeus.aegeus.game.planets;

import org.bukkit.entity.Player;

import com.aegeus.aegeus.game.Statistics;
import com.aegeus.aegeus.util.Helper;

public class PlanetManager {
	public static void warpPlayer(Player player, Planet planet){
		if(Statistics.playerData.get(player).CurrentPlanet.equals(planet)){
			player.sendMessage(Helper.colorCodes("&cYou are already on " + planet.getName() + "."));
		} else {
			player.sendMessage(Helper.colorCodes("&bYou are heading to planet " + planet.getName() + "..."));
			player.sendMessage(Helper.colorCodes("&7&o" + planet.getDescription()));
			player.teleport(planet.getLocation());
			Statistics.playerData.get(player).CurrentPlanet = planet;
		}
	}
}
