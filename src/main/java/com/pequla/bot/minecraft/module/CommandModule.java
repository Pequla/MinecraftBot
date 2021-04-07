package com.pequla.bot.minecraft.module;

import com.pequla.bot.minecraft.MinecraftBot;
import com.pequla.bot.minecraft.command.*;
import net.dv8tion.jda.api.entities.TextChannel;
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
        String prefix = plugin.getPrefix();
        StatusCommand status = new StatusCommand();
        map.put(prefix + "status", status);
        map.put(prefix + "online", status);
        map.put(prefix + "seed", new SeedCommand());
        map.put(prefix + "spawn", new SpawnCommand());
        map.put(prefix + "rcon", new RconCommand());
        map.put(prefix + "ip", new IpCommand());
        map.put(prefix + "help", new HelpCommand());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            TextChannel channel = event.getChannel();
            if (channel.getId().equals(plugin.getBotCommandsChannel())) {
                String[] message = event.getMessage().getContentRaw().trim().split("\\s+");
                GuildCommand command = map.get(message[0]);
                if (command != null) {
                    command.execute(plugin, event.getMember(), channel, Arrays.copyOfRange(message, 1, message.length));
                }
            }
        }
    }
}
