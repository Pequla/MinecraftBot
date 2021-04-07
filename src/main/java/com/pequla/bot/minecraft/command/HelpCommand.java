package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.awt.*;
import java.time.Instant;

public class HelpCommand implements GuildCommand {
    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        Guild guild = channel.getGuild();
        channel.sendMessage(new EmbedBuilder()
                .setColor(Color.GRAY)
                .setTitle(MarkdownUtil.bold("COMMAND LIST"))
                .addField("!online/!status", "Displays online players and server version", false)
                .addField("!seed", "Displays the current world seed", false)
                .addField("!spawn", "Displays the current world spawn location", false)
                .addField("!ip", "Displays server's ip address", false)
                .addField("!rcon", "Executes a minecraft command", false)
                .setFooter(guild.getName(), guild.getIconUrl())
                .setTimestamp(Instant.now())
                .build()).queue();
    }
}
