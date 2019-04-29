package io.github.theluca98.automate.module

import io.github.theluca98.automate.AutomateModule
import org.bukkit.Material
import org.bukkit.block.data.type.Piston
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPistonExtendEvent

object PistonBreakModule : AutomateModule {

    @EventHandler
    private fun onPistonExtend(e: BlockPistonExtendEvent) {
        val piston = e.block.blockData as Piston
        val i = e.blocks.map { it.type }.indexOf(Material.END_ROD)
        if (i >= 0 && e.blocks.size > i + 1) {
            schedule {
                e.blocks[i + 1].getRelative(piston.facing).breakNaturally()
            }
        }
    }

}