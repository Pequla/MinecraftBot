package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.Location;

import java.awt.*;
import java.time.Instant;

public class SpawnCommand implements GuildCommand {

    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        Guild guild = channel.getGuild();
        Location location = plugin.getServer().getWorlds().get(0).getSpawnLocation();
        channel.sendMessage(
                new EmbedBuilder()
                        .setColor(Color.GRAY)
                        .setTitle(MarkdownUtil.bold("SPAWN LOCATION"))
                        .setDescription(MarkdownUtil.bold(String.format("%.2f %.2f %.2f", location.getX(), location.getY(), location.getZ())))
                        .setFooter(guild.getName(), guild.getIconUrl())
                        .setTimestamp(Instant.now())
                        .build()
        ).queue();
    }
}
