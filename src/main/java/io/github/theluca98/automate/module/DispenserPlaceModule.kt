package io.github.theluca98.automate.module

import io.github.theluca98.automate.AutomateModule
import org.bukkit.Material
import org.bukkit.block.Container
import org.bukkit.block.data.type.Dispenser
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockDispenseEvent

object DispenserPlaceModule : AutomateModule {

    // These blocks already have a vanilla behaviour and we don't need to override it
    // Source: https://minecraft.gamepedia.com/Dispenser
    private val ignored = listOf(Material.TNT, Material.CARVED_PUMPKIN)
            .plus(Material.values().filter { it.name.endsWith("HEAD") })
            .plus(Material.values().filter { it.name.endsWith("SHULKER_BOX") })

    @EventHandler
    private fun onDispense(e: BlockDispenseEvent) {
        if (e.block.blockData is Dispenser && e.item.type.isBlock && !ignored.contains(e.item.type)) {
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

}