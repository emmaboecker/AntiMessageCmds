package net.stckoverflw.antimessagecmds.listener

import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.rest.builder.message.create.allowedMentions
import net.stckoverflw.antimessagecmds.MessageListenerModule
import net.stckoverflw.antimessagecmds.config.Config
import net.stckoverflw.antimessagecmds.util.translateString

suspend fun MessageListenerModule.messageListener() = event<MessageCreateEvent> {
    action {
        if (event.message.content.matches(Config.MESSAGE_REGEX)) {
            if (Config.RESPONSE_OVERRIDE == null) {
                event.message.channel.createMessage {
                    content = translateString("message.use_slash_commands")
                }
            } else {
                event.message.reply {
                    content = Config.RESPONSE_OVERRIDE!!.replace("\\\\n".toRegex(), '\n'.toString())
                    this.allowedMentions {
                        this.repliedUser = false
                    }
                }
            }
        }
    }
}