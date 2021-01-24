package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.Server;

import java.awt.*;
import java.time.Instant;

public class RconCommand implements GuildCommand {

    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        Guild guild = channel.getGuild();
        User author = member.getUser();
        if (guild.getId().equals(plugin.getDiscordServer())) {
            if (hasRole(member, plugin.getAdminRole())) {
                Server server = plugin.getServer();
                server.getScheduler().runTask(plugin, () -> {
                    if (server.dispatchCommand(server.getConsoleSender(), String.join(" ", args))) {
                        channel.sendMessage(
                                new EmbedBuilder()
                                        .setColor(Color.GREEN)
                                        .setAuthor(author.getName(), null, author.getAvatarUrl())
                                        .setTitle(MarkdownUtil.bold("SUCCESS"))
                                        .setDescription(MarkdownUtil.bold("Command executed successfully!"))
                                        .setFooter(guild.getName(), guild.getIconUrl())
                                        .setTimestamp(Instant.now())
                                        .build()
                        ).queue();
                    } else {
                        channel.sendMessage(
                                new EmbedBuilder()
                                        .setColor(Color.RED)
                                        .setAuthor(author.getName(), null, author.getAvatarUrl())
                                        .setTitle(MarkdownUtil.bold("ERROR"))
                                        .setDescription(MarkdownUtil.bold("Command execution failed, please check if the command exists!"))
                                        .setFooter(guild.getName(), guild.getIconUrl())
                                        .setTimestamp(Instant.now())
                                        .build()
                        ).queue();
                    }
                });
            }
        }
    }

    private boolean hasRole(Member member, String roleId) {
        return member.getRoles().stream().anyMatch(role -> role.getId().equals(roleId));
    }

}
