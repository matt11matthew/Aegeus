package com.aegeus.aegeus.game.planets;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PlanetXylo implements Planet {

	private static final String ID = "planet_xylo";
	private static final String name = "Xylo";
	private static final String description = "A temporary planet with tons of gold resources.";
	private static final Location location = Bukkit.getWorld(ID).getSpawnLocation();
	
	@Override
	public String getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Location getLocation() {
		return location;
	}
	
}
