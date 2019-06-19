package work.upstarts.editorjskit.models

import work.upstarts.editorjskit.models.data.*

abstract class EJBlock(
    open val type: EJAbstractBlockType,
    open val data: EJData
)

class EJCustomBlock(
    override val type: EJAbstractBlockType,
    override val data: EJData
): EJBlock(type, data)

data class EJHeaderBlock(
    override val type: EJAbstractBlockType,
    override val data: EJHeaderData
): EJBlock(type, data)

data class EJParagraphBlock(
    override val type: EJAbstractBlockType,
    override val data: EJParagraphData
): EJBlock(type, data)

data class EJListBlock(
    override val type: EJAbstractBlockType,
    override val data: EJListData
): EJBlock(type, data)

data class EJDelimiterBlock(
    override val type: EJAbstractBlockType,
    override val data: EJData
): EJBlock(type, data)

data class EJImageBlock(
    override val type: EJAbstractBlockType,
    override val data: EJImageData
): EJBlock(type, data)

data class EJRawHtmlBlock(
    override val type: EJAbstractBlockType,
    override val data: EJRawHtmlData
): EJBlock(type, data)


data class EJTableBlock(
    override val type: EJAbstractBlockType,
    override val data: EJTableData
): EJBlock(type, data)