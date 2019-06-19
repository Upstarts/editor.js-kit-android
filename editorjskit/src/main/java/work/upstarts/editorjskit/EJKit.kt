package work.upstarts.editorjskit

import work.upstarts.editorjskit.models.EJAbstractCustomBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

object EJKit {
    var ejStyle: EJStyle? = null

    private val customBlocks: MutableList<EJAbstractCustomBlock> = mutableListOf()

    fun register(customBlock: EJAbstractCustomBlock) {
        customBlocks.add(customBlock)
    }

    fun getCustomBlocks(): List<EJAbstractCustomBlock> {
        return customBlocks
    }
}