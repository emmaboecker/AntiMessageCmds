package net.stckoverflw.antimessagecmds.config

import dev.schlaubi.mikbot.plugin.api.EnvironmentConfig

object Config : EnvironmentConfig("") {

    val MESSAGE_REGEX: Regex by getEnv {
        it.toRegex()
    }

    val RESPONSE_OVERRIDE: String? by environment.optional()
}