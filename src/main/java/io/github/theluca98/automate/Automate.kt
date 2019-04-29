package io.github.theluca98.automate

import io.github.theluca98.automate.module.DispenserPlaceModule
import io.github.theluca98.automate.module.PistonBreakModule
import org.bukkit.plugin.java.JavaPlugin

class Automate : JavaPlugin() {

    companion object {
        lateinit var plugin: Automate
    }

    private val modules = arrayOf(PistonBreakModule, DispenserPlaceModule)

    override fun onEnable() {
        plugin = this
        saveDefaultConfig()
        modules.forEach { it.enable() }
    }

}