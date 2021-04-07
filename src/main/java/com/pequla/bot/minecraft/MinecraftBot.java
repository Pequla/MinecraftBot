package com.pequla.bot.minecraft;

import com.pequla.bot.minecraft.event.PlayerDeath;
import com.pequla.bot.minecraft.event.PlayerJoin;
import com.pequla.bot.minecraft.event.PlayerLeave;
import com.pequla.bot.minecraft.module.CommandModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

public final class MinecraftBot extends JavaPlugin {

    private final Logger logger;
    private final String token;
    private final String address;
    private final String discordServer;
    private final String adminRole;
    private final String deathChannel;
    private final String joinLeaveChannel;
    private final String botCommandsChannel;
    private JDA jda;

    public MinecraftBot() {
        this.logger = getLogger();
        logger.info("Loading configuration");
        FileConfiguration config = getConfig();
        config.options().copyDefaults();
        saveDefaultConfig();
        this.token = config.getString("token");
        this.address = config.getString("server-ip");
        this.discordServer = config.getString("id.discord-server");
        this.adminRole = config.getString("id.admin-role");
        this.deathChannel = config.getString("id.death-channel");
        this.joinLeaveChannel = config.getString("id.join-leave-channel");
        this.botCommandsChannel = config.getString("bot-commands-channel");
    }

    @Override
    public void onEnable() {
        if (!token.equals("bot-token-goes-here")) {
            logger.info("Connecting to Discord API");
            try {
                jda = JDABuilder.createDefault(token)
                        .setActivity(Activity.playing("Minecraft"))
                        .addEventListeners(new CommandModule(this))
                        .build();
                try {
                    jda.awaitReady();
                    logger.info("Successfully connected to Discord API");
                    SelfUser bot = jda.getSelfUser();
                    logger.info("Name: " + bot.getName());
                    logger.info("ID: " + bot.getId());
                    logger.info("Servers: " + jda.getGuilds().size());
                } catch (InterruptedException e) {
                    handleException(e);
                }
            } catch (LoginException e) {
                handleException(e);
            }
            Server server = getServer();
            server.getPluginManager().registerEvents(new PlayerJoin(this), this);
            server.getPluginManager().registerEvents(new PlayerLeave(this), this);
            server.getPluginManager().registerEvents(new PlayerDeath(this), this);
        } else {
            logger.warning("Bot token hasn't been configured");
            logger.warning("Make sure you change it in config.yml");
        }
    }

    @Override
    public void onDisable() {
        if (jda != null) {
            logger.info("Disconnecting from Discord API");
            jda.shutdownNow();
        }
    }

    private void handleException(Exception e) {
        logger.severe("Exception occurred: " + e.getClass().getName());
        logger.severe("With message: " + e.getMessage());
    }

    public String getAddress() {
        return address;
    }

    public String getDiscordServer() {
        return discordServer;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public String getDeathChannel() {
        return deathChannel;
    }

    public String getJoinLeaveChannel() {
        return joinLeaveChannel;
    }

    public String getBotCommandsChannel() {
        return botCommandsChannel;
    }

    public JDA getJda() {
        return jda;
    }
}
