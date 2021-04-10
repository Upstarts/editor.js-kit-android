package work.upstarts.editorjskit.models

import work.upstarts.editorjskit.models.data.*

interface EJBlock{
    val type: EJAbstractBlockType
    val data: EJData
}

class EJCustomBlock(
    override val type: EJAbstractBlockType,
    override val data: EJData
): EJBlock

data class EJHeaderBlock(
    override val type: EJAbstractBlockType,
    override val data: EJHeaderData
): EJBlock

data class EJParagraphBlock(
    override val type: EJAbstractBlockType,
    override val data: EJParagraphData
): EJBlock

data class EJListBlock(
    override val type: EJAbstractBlockType,
    override val data: EJListData
): EJBlock

data class EJDelimiterBlock(
    override val type: EJAbstractBlockType,
    override val data: EJData
): EJBlock

data class EJImageBlock(
    override val type: EJAbstractBlockType,
    override val data: EJImageData
): EJBlock

data class EJRawHtmlBlock(
    override val type: EJAbstractBlockType,
    override val data: EJRawHtmlData
): EJBlock

data class EJTableBlock(
    override val type: EJAbstractBlockType,
    override val data: EJTableData
): EJBlock