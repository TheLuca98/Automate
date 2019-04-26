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
        if (e.blocks.size >= 2 && e.blocks[0].type == Material.END_ROD) {
            schedule {
                e.blocks[1].getRelative(piston.facing).breakNaturally()
            }
        }
    }

}