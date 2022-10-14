import dev.schlaubi.mikbot.gradle.GenerateDefaultTranslationBundleTask
import java.util.Locale

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.7.20"
    id("com.google.devtools.ksp") version "1.7.20-1.0.7"
    id("dev.schlaubi.mikbot.gradle-plugin") version "2.6.3"

    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

ktlint {
    disabledRules.set(listOf("no-wildcard-imports"))
}

group = "net.stckoverflw"
version = "1.0.5"

repositories {
    mavenCentral()
    maven("https://schlaubi.jfrog.io/artifactory/mikbot/")
    maven("https://schlaubi.jfrog.io/artifactory/envconf/")
    maven("https://maven.kotlindiscord.com/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly("dev.schlaubi:mikbot-api:2.3.1-SNAPSHOT")
    ksp("dev.schlaubi:mikbot-plugin-processor:1.1.1")
}

mikbotPlugin {
    provider.set("StckOverflw")
    license.set("AGPL-3.0 License")
    description.set("Sends a message to your bot's users to use slash commands when they try to use message")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    task<Copy>("buildAndInstall") {
        dependsOn(assemblePlugin)
        from(assemblePlugin)
        include("*.zip")
        into("plugins")
    }

    val generateDefaultResourceBundle = task<GenerateDefaultTranslationBundleTask>("generateDefaultResourceBundle") {
        defaultLocale.set(Locale("en", "GB"))
    }

    assemblePlugin {
        dependsOn(generateDefaultResourceBundle)
    }

    installBot {
        botVersion.set("2.3.1-SNAPSHOT")
    }

    pluginPublishing {
        repositoryUrl.set("https://antimessagecmds.mikbot.stckoverflw.net")
        targetDirectory.set(rootProject.file("ci-repo").toPath())
        projectUrl.set("https://github.com/StckOverflw/AntiMessageCmds")
    }
}