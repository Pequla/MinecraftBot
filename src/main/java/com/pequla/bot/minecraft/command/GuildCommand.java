package com.pequla.bot.minecraft.command;

import com.pequla.bot.minecraft.MinecraftBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public interface GuildCommand {
    void execute(MinecraftBot plugin, Member member, TextChannel channel, String[] args);
}
