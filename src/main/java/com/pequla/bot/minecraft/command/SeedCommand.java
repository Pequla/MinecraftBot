package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.awt.*;
import java.time.Instant;

public class SeedCommand implements GuildCommand {

    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        Guild guild = channel.getGuild();
        channel.sendMessage(
                new EmbedBuilder()
                        .setColor(Color.GRAY)
                        .setTitle(MarkdownUtil.bold("WORLD SEED"))
                        .setDescription(MarkdownUtil.bold(String.valueOf(plugin.getServer().getWorlds().get(0).getSeed())))
                        .setFooter(guild.getName(), guild.getIconUrl())
                        .setTimestamp(Instant.now())
                        .build()
        ).queue();
    }
}
