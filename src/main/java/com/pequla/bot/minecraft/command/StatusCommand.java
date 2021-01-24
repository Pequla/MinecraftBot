package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class StatusCommand implements GuildCommand {

    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        Guild guild = channel.getGuild();
        Server server = plugin.getServer();
        List<String> online = server.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        channel.sendMessage(
                new EmbedBuilder()
                        .setColor(Color.GRAY)
                        .setTitle(MarkdownUtil.bold("SERVER STATUS"))
                        .setThumbnail(channel.getJDA().getSelfUser().getAvatarUrl())
                        .addField("Version:", server.getVersion(), true)
                        .addField("Online players:", String.valueOf(online.size()), true)
                        .addField("Player list:", playerListFormatter(online), false)
                        .setFooter(guild.getName(), guild.getIconUrl())
                        .setTimestamp(Instant.now())
                        .build()
        ).queue();
    }

    private String playerListFormatter(List<String> online) {
        if (online.size() == 0) {
            return "Server is empty";
        }
        return online.toString();
    }
}
