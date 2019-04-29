package io.github.theluca98.automate.module

import io.github.theluca98.automate.AutomateModule
import org.bukkit.Material
import org.bukkit.block.Container
import org.bukkit.block.data.type.Dispenser
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockDispenseEvent

object DispenserPlaceModule : AutomateModule {

    @EventHandler
    private fun onDispense(e: BlockDispenseEvent) {
        if (e.block.type == Material.DISPENSER && e.item.type.isSolid && !isIgnored(e.item.type)) {
            val dispenser = e.block.blockData as Dispenser
            val relative = e.block.getRelative(dispenser.facing)
            if (relative.isEmpty) {
                e.isCancelled = true
                relative.type = e.item.type
                schedule {
                    val container = e.block.state as Container
                    container.inventory.removeItem(e.item)
                }
            }
        }
    }

    private fun isIgnored(type: Material): Boolean {
        return getConfig()?.getStringList("ignore")?.contains(type.key.toString()) ?: false
    }

}