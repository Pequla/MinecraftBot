package com.pequla.bot.minecraft.event;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private final MinecraftBot plugin;

    public PlayerChat(MinecraftBot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (event.getRecipients().size() == plugin.getServer().getOnlinePlayers().size()) {
            Player player = event.getPlayer();
            String message = event.getMessage();
            String id = plugin.getChatChannel();
            if (id != null && !id.equals("channel-id")) {
                TextChannel channel = plugin.getJda().getTextChannelById(id);
                if (channel != null && channel.canTalk()) {
                    channel.sendMessage("**" + player.getDisplayName() + ":**" + message).queue();
                } else {
                    plugin.getLogger().severe("Bot cant talk in the channel configured");
                }
            } else {
                plugin.getLogger().severe("Chat link channel not configured");
            }
        }
    }
}
