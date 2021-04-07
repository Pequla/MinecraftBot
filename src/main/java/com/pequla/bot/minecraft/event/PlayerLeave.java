package com.pequla.bot.minecraft.event;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.time.Instant;

public class PlayerLeave implements Listener {

    private final MinecraftBot plugin;

    public PlayerLeave(MinecraftBot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {
        Guild guild = plugin.getJda().getGuildById(plugin.getDiscordServer());
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById(plugin.getJoinLeaveChannel());
            if (channel != null) {
                String player = event.getPlayer().getName();
                Server server = plugin.getServer();
                channel.sendMessage(
                        new EmbedBuilder()
                                .setColor(Color.RED)
                                .setTitle(MarkdownUtil.bold(player.toUpperCase() + " LEFT"))
                                .setDescription(MarkdownUtil.bold("Player " + player + " left the game"))
                                .addField("Currently online:", String.format("%d/%d", server.getOnlinePlayers().size() - 1, server.getMaxPlayers()), true)
                                .setFooter(guild.getName(), guild.getIconUrl())
                                .setTimestamp(Instant.now())
                                .build()
                ).queue();
            }
        }
    }
}
