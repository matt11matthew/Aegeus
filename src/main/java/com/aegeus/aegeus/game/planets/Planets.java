package com.aegeus.aegeus.game.planets;

public enum Planets {
	TERMINAL(new Terminal()),
	PLANET_XYLO(new PlanetXylo());
	
	Planet planet;
	
	Planets(Planet planet){
		this.planet = planet;
	}
	
	public Planet getPlanet(){
		return planet;
	}
}
