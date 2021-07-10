package com.pequla.bot.minecraft.module;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class ChatModule extends ListenerAdapter {

    private final MinecraftBot plugin;

    public ChatModule(MinecraftBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();

        // In these cases we reject the check
        if (author.isBot() || author.isSystem() || message.isWebhookMessage()) {
            return;
        }

        TextChannel channel = event.getChannel();
        if (channel.getId().equals(plugin.getChatChannel())) {
            plugin.getServer().broadcastMessage(ChatColor.DARK_PURPLE + "[Discord] " + author.getAsTag() + ": " +
                    ChatColor.WHITE + message.getContentRaw());
        }
    }
}
