package aegeus.com.aegeus.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aegeus.com.aegeus.obj.AegeusArmor;
import aegeus.com.aegeus.types.ItemRarity;

public class CommandTestArmor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
		
		Player player = (Player) sender;
		
		AegeusArmor armor = new AegeusArmor(Material.CHAINMAIL_LEGGINGS);
		armor.setName("&dAgile Chain Leggings");
		armor.setHp(245);
		armor.setEnergyRegen(4);
		armor.setTier(2);
		armor.setRarity(ItemRarity.UNIQUE);
		player.getInventory().addItem(armor.build());
		
		return true;
	}

}
