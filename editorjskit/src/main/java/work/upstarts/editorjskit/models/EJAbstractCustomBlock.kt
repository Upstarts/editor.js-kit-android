package work.upstarts.editorjskit.models

import work.upstarts.editorjskit.models.data.EJData

open class EJAbstractCustomBlock(
    val type: EJAbstractBlockType,
    val data: Class<out EJData>
)