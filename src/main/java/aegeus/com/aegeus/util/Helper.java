package aegeus.com.aegeus.util;

import org.bukkit.ChatColor;

public class Helper {
	/**
	 * Calculates rarity based on circular interpolation.
	 * 
	 * @param i
	 *            Interpolation value (0.0-1.0).
	 * @param min
	 *            Minimum value.
	 * @param max
	 *            Maximum value.
	 * @return Interpolated value.
	 */
	public static float calcRarity(float i, float min, float max) {
		i /= 1;
		return (float) (-max * (Math.sqrt(1 - i * i) - 1) + min);
	}
	
	public static String colorCodes(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static int calcMaxXP(int Level){
		return (int) Math.round(50 * (Level * (Level * 0.4)));
	}
	
	public static int calcWepLevelBuff(int MinDmg, int Level){
		return (int) Math.round((MinDmg * Level) / 65);
	}
	
}
