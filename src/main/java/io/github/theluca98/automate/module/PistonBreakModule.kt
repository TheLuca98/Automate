package io.github.theluca98.automate.module

import io.github.theluca98.automate.AutomateModule
import org.bukkit.Material
import org.bukkit.block.data.Directional
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPistonExtendEvent

object PistonBreakModule : AutomateModule {

    @EventHandler
    private fun onPistonExtend(e: BlockPistonExtendEvent) {
        e.blocks.filter { it.type == Material.END_ROD }.forEach {
            val direction = (it.blockData as Directional).facing
            val target = it.getRelative(direction)
            if (!target.isEmpty && e.blocks.contains(target)) {
                schedule {
                    target.getRelative(direction).breakNaturally()
                }
            }
        }
    }

}