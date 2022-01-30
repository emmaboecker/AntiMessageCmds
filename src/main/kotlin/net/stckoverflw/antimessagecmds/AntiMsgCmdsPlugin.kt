package net.stckoverflw.antimessagecmds

import com.kotlindiscord.kord.extensions.builders.ExtensibleBotBuilder
import dev.schlaubi.mikbot.plugin.api.Plugin
import dev.schlaubi.mikbot.plugin.api.PluginMain
import dev.schlaubi.mikbot.plugin.api.PluginWrapper
import net.stckoverflw.antimessagecmds.listener.messageListener
import org.pf4j.Extension
import com.kotlindiscord.kord.extensions.extensions.Extension as KordExtension

@PluginMain
class AntiMsgCmdsPlugin(wrapper: PluginWrapper) : Plugin(wrapper) {

    override fun ExtensibleBotBuilder.ExtensionsBuilder.addExtensions() {
        add(::MessageListenerModule)
    }

}

@Extension
class MessageListenerModule : KordExtension() {
    override val name: String = "listener for messages"

    override suspend fun setup() {
        messageListener()
    }
}
