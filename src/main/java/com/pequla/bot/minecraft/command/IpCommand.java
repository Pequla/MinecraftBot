package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class IpCommand implements GuildCommand {
    @Override
    public void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args) {
        channel.sendMessage(String.format("Server address: **%s**", plugin.getAddress())).queue();
    }
}
