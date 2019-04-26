package io.github.theluca98.automate

import org.bukkit.Bukkit
import org.bukkit.event.Listener

interface AutomateModule : Listener {

    fun enable() {
        Automate.plugin.logger.info("Enabling ${javaClass.simpleName}")
        Bukkit.getPluginManager().registerEvents(this, Automate.plugin)
    }

    fun schedule(task: () -> Unit) {
        Bukkit.getScheduler().runTask(Automate.plugin, task)
    }

}