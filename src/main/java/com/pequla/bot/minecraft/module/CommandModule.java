package com.pequla.bot.minecraft.module;

import com.pequla.bot.minecraft.MinecraftBot;
import com.pequla.bot.minecraft.command.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class CommandModule extends ListenerAdapter {

    private final MinecraftBot plugin;
    private final HashMap<String, GuildCommand> map;

    public CommandModule(MinecraftBot plugin) {
        this.plugin = plugin;
        this.map = new HashMap<>();
        map.put(plugin.getPrefix() + "status", new StatusCommand());
        map.put(plugin.getPrefix() + "online", new StatusCommand());
        map.put(plugin.getPrefix() + "seed", new SeedCommand());
        map.put(plugin.getPrefix() + "spawn", new SpawnCommand());
        map.put(plugin.getPrefix() + "rcon", new RconCommand());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String[] message = event.getMessage().getContentRaw().trim().split("\\s+");
            GuildCommand command = map.get(message[0]);
            if (command != null) {
                command.execute(plugin, event.getMember(), event.getChannel(), Arrays.copyOfRange(message, 1, message.length));
            }
        }
    }
}
