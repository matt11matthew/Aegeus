package aegeus.com.aegeus;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import aegeus.com.aegeus.methods.ChatMethods;
import aegeus.com.aegeus.types.ChatChannel;
import aegeus.com.aegeus.util.Helper;

public class Chat implements Listener {

	private JavaPlugin parent;

	public Chat(JavaPlugin parent) {
		this.parent = parent;
	}
	
	public static HashMap<Player, Player> playerReply = new HashMap<>();
	public static HashMap<Player, ChatChannel> playerChatChannel = new HashMap<>();

	@EventHandler
	// Local messages and custom message format
	private void onChatEvent(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		if(!Chat.playerChatChannel.containsKey(player)) Chat.playerChatChannel.put(player, ChatChannel.LOCAL);
		if (Chat.playerChatChannel.get(player).equals(ChatChannel.GLOBAL)) {
			ChatMethods.sendGlobalChat(player, Helper.colorCodes(event.getMessage()));
		} else {
			ChatMethods.sendLocalChat(player, Helper.colorCodes(event.getMessage()));
		}
	}
}
