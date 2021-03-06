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
        val mapping = getMapping(e.item.type)
        if (e.block.type == Material.DISPENSER && mapping.isBlock && !isIgnored(e.item.type)) {
            val dispenser = e.block.blockData as Dispenser
            val target = e.block.getRelative(dispenser.facing)
            if (target.isEmpty) {
                e.isCancelled = true
                target.setType(mapping, true)
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

    private fun getMapping(type: Material): Material {
        val map = getConfig()?.getMapList("mappings")
        val data = map?.firstOrNull { it["from"]?.equals(type.key.toString()) ?: false }
        return if (data != null) {
            Material.matchMaterial(data["to"] as String) ?: type
        } else {
            type
        }
    }

}