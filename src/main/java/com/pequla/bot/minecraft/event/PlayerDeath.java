package com.pequla.bot.minecraft.event;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class PlayerDeath implements Listener {

    private final MinecraftBot plugin;

    public PlayerDeath(MinecraftBot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Guild guild = plugin.getJda().getGuildById(plugin.getDiscordServer());
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById(plugin.getDeathChannel());
            Location location = player.getLocation();
            if (channel != null) {
                String playerName = player.getName();
                channel.sendMessage(
                        new EmbedBuilder()
                                .setColor(Color.GRAY)
                                .setTitle(MarkdownUtil.bold(playerName.toUpperCase() + " DIED"))
                                .setDescription(ChatColor.stripColor(event.getDeathMessage()))
                                .addField("Location:", String.format("%.2f %.2f %.2f", location.getX(), location.getY(), location.getZ()), false)
                                .addField("World:", getWorldName(Objects.requireNonNull(location.getWorld())), false)
                                .setFooter(guild.getName(), guild.getIconUrl())
                                .setTimestamp(Instant.now())
                                .build()
                ).queue();
            }
        }
    }

    private String getWorldName(World world) {
        String worldName = world.getName();
        switch (worldName) {
            case "world":
                worldName = "Overworld";
                break;
            case "world_nether":
                worldName = "Nether";
                break;
            case "world_the_end":
                worldName = "The End";
                break;
            default:
                worldName = "Unknown";
                break;
        }
        return worldName;
    }
}
