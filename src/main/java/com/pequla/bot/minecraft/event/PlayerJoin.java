package com.pequla.bot.minecraft.event;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.time.Instant;

public class PlayerJoin implements Listener {

    private final MinecraftBot plugin;

    public PlayerJoin(MinecraftBot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Guild guild = plugin.getJda().getGuildById(plugin.getDiscordServer());
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById(plugin.getJoinLeaveChannel());
            if (channel != null) {
                String player = event.getPlayer().getName();
                channel.sendMessage(
                        new EmbedBuilder()
                                .setColor(Color.GREEN)
                                .setTitle(MarkdownUtil.bold(player.toUpperCase() + " JOINED"))
                                .setDescription(MarkdownUtil.bold("Player " + player + " joined the game"))
                                .addField("Currently online:", String.valueOf(plugin.getServer().getOnlinePlayers().size()), true)
                                .setFooter(guild.getName(), guild.getIconUrl())
                                .setTimestamp(Instant.now())
                                .build()
                ).queue();
            }
        }
    }
}
