package aegeus.com.aegeus.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aegeus.com.aegeus.obj.AegeusWeapon;
import aegeus.com.aegeus.types.ItemRarity;

public class CommandTestWeapon implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
		
		Player player = (Player) sender;
		
		AegeusWeapon wep = new AegeusWeapon(Material.DIAMOND_AXE);
		wep.setName("&dMordebit Axe of Ice Fire");
		wep.setDmg(165, 213);
		wep.setFireDmg(18);
		wep.setIceDmg(12);
		wep.setLifeSteal(9);
		wep.setTier(4);
		wep.setLevel(1);
		wep.setRarity(ItemRarity.RARE);
		
		player.getInventory().addItem(wep.build());
		return true;
	}

}
