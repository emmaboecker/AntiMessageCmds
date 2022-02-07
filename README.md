# Anti Message Commands
Your bot used message commands but you switched to Slash-Commands?
Use this Plugin to send your bot's users a message when they try to use your old commands

Well, you could also just use this as an auto respond for specific phrases actually

# Setup

1. Add `https://antimessagecmds.mikbot.stckoverflw.net` to your `PLUGIN_REPOSITORIES` environment variable
2. Add `antimessagecmds` to your `DOWNLOAD_PLUGINS` environment variable
3. Add `MESSAGE_REGEX` to your environment variables with the following value:
   1. `PREFIX(COMMAND)(.*)` (Replace PREFIX and COMMAND with your old message-command prefix and a `|` seperated list of commands the bot should react to, or just one command)
4. (Optional) if you don't want to use the default translated message, you can add `RESPONSE_OVERRIDE` to your environment variables and set a custom message
