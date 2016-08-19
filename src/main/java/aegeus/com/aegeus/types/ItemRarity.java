package aegeus.com.aegeus.types;

import aegeus.com.aegeus.util.Helper;

public enum ItemRarity {
	STARTER("&9&oStarter"),
	STANDARD("&7&oStandard"),
	UNCOMMON("&a&oUncommon"),
	RARE("&b&oRare"),
	UNIQUE("&e&oUnique"),
	DUNGEON("&c&oDungeon Loot");
	
	public String str;
	
	ItemRarity(String str){
		this.str = Helper.colorCodes(str);
	}
}
