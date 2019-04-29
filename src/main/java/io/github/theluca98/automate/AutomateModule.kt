package io.github.theluca98.automate

import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.event.Listener

interface AutomateModule : Listener {

    fun enable() {
        if (shouldEnable()) {
            Automate.plugin.logger.info("Enabling ${javaClass.simpleName}")
            Bukkit.getPluginManager().registerEvents(this, Automate.plugin)
        } else {
            Automate.plugin.logger.info("Skipping ${javaClass.simpleName}")
        }
    }

    fun schedule(task: () -> Unit) {
        Bukkit.getScheduler().runTask(Automate.plugin, task)
    }

    fun getConfig(): ConfigurationSection? = Automate.plugin.config.getConfigurationSection(javaClass.simpleName)

    fun shouldEnable() = getConfig()?.getBoolean("enabled", false) ?: false

}