# Minecraft Bot
Minecraft Bot is a simple discord bot integration as a Spigot plugin that is both fast and eazy to configure. You will require a Spigot server on version 1.16.4 or later, as well as a created Discord Application with a live Bot. Recommended for SMP servers!

## Installation
Before we begin you need to create an application for your bot on the [Discord Developer Portal](https://discord.com/developers/applications/). After you have created the application and added you prefered server icon as the applications icon go to the bot tab and press Add a bot. After you done so go to the OAuth tab and selec the scope to be bot, and select Administartor as a permission, then copy the link in the browser and invite the bot to your Discord Server. After you done that go back to the Bot tab and copy the TOKEN

In order to follow this tutorial you will also need to have the Discord developer mode enabled. After you enable it you will be able to copy IDs of messages, roles, users and bots!

### Plugin setup
Download the plugin from the [project's releases](https://github.com/Pequla/MinecraftBot/releases/latest) and put it in your plugins folder, then run the server once in order to generate the configuration file.
Open the plugins folder and then the MinecraftBot folder. In there you should find config.yml, open it.

It should look something like this
```yaml
# For more details please visit the github page (https://github.com/Pequla/MinecraftBot)
token: bot-token-goes-here
server-ip: your-server-address
command-prefix: !
id:
  discord-server: discord-server-id
  admin-role: role-id
  death-channel: channel-id
  join-leave-channel: channel-id
  bot-commands-channel: channel-id

```
Now replace  `bot-token-goes-here` with the bot token you copied from the Discord developer page of your application. Chose your own command prefix, but you can also stick with defaults.

We need to cover the ID section. In order to retrieve the Discord server's ID you need to right click on the discord server icon and select Copy ID, after you done so paste it instead of `discord-server-id` . In order to get the administrator role ID (**people with this role will be able to execute the commands on the server directly from Discord**) we need to open the Server settings and navigate to the roles. Right click the role you want or create one and then press Copy ID, paste it instead of `role-id`.

For channels you will need to right click on the channel and select Copy ID, ideally I recommend you to make 2 fresh channels just for this, it will look much more proffesional. Replace the `channel-id` place holder respectivley

After you have done so save the config file and restart the server, the bot you created should appear online. Your bot will be online as long as the Minecraft server is online (that should be expected since its running on the server)

## Commands
There are few commands you can use:
 
- Status Command `<prefix>status` and `<prefix>online` - Displayes a formated status of the server, what version is it running on, how many players are online and there respectable names
- Seed Command `<prefix>seed` - Displayes the current world's seed
- Spawn Command `<prefix>spawn` - Displays the overworld's spawn point.
- Rcon Command `<prefix>rcon` - Allows server members with certain role to execute the commands on the Minecraft server directly, this feature is extreamly secure and there is no way to bypass restrictions. If you've been using rcon client for a long time, disable it and use this feature instead. Command should be in form of `<prefix>rcon <minecraft-command>`.
- IP Command `<prefix>ip` - Displays the server ip address
- Help Command `<prefix>help` - Displays a list of available commands

## Other features
With this plugin you get a player death notification on discord as well as the full coordinates where the player died even with a world notation to avoid any confustion. This is a must if you are running an SMP server.

Player join leave notifications also exist and they are self explanatory, when the player joins or leaves the game it will display the message in the channel specified as well as the total number of players at that time. This feature will make your server much more active since other players will join instantly if they see somone else is online

Custom bot commands channel, this way you dont need to fiddle arround with permissions, all you need to do is to change the `channel-id` place holder under the `bot-commands-channel` property with your bot only commands channel id. Bot will listen only in that channel for commands.

## More information
This plugin is extremely light weight and will consume less than 30MB of RAM while running, it might seam a lot but the way you communicate with Discord is really complicated and in order to keep the connection running we need a little bit more RAM. In terms of bandwith no worries there, the bot will consume an average of 10MB per month.

## Libraries
Huge thanks for making this plugin possible goes to [Java Discord API or short JDA](https://github.com/DV8FromTheWorld/JDA),  and to the Spigot team.
