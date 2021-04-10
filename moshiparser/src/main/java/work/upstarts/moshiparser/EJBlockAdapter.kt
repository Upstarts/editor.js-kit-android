package work.upstarts.moshiparser

import com.squareup.moshi.*
import work.upstarts.editorjskit.EJKit
import work.upstarts.editorjskit.models.*
import work.upstarts.editorjskit.models.data.*

class EJBlockAdapter(
    private val moshi: Moshi
) {
    @FromJson
    fun fromJson(jsonData: Map<String, Any>): EJBlock? {
        val type = jsonData["type"].toString()
        val data = jsonData["data"] ?: throw IllegalStateException("Data cannot be null")
        try {
            return when (val ejBlockType = EJBlockType.fromString(type)) {
                EJBlockType.HEADER -> {
                    EJHeaderBlock(
                        ejBlockType,
                        moshi.adapter(EJHeaderData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJHeaderData::class.java.name}")
                    )
                }
                EJBlockType.PARAGRAPH -> {
                    EJParagraphBlock(
                        ejBlockType,
                        moshi.adapter(EJParagraphData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJParagraphData::class.java.name}")
                    )
                }
                EJBlockType.LIST -> {
                    EJListBlock(
                        ejBlockType,
                        moshi.adapter(EJListData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJListData::class.java.name}")
                    )
                }
                EJBlockType.DELIMITER -> {
                    EJDelimiterBlock(ejBlockType, EJDelimiterData())
                }
                EJBlockType.IMAGE -> {
                    EJImageBlock(
                        ejBlockType,
                        moshi.adapter(EJImageData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJImageData::class.java.name}")
                    )
                }
                EJBlockType.TABLE -> {
                    EJTableBlock(
                        ejBlockType,
                        moshi.adapter(EJTableData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJTableData::class.java.name}")
                    )
                }
                EJBlockType.RAW_HTML -> {
                    EJRawHtmlBlock(
                        ejBlockType,
                        moshi.adapter(EJRawHtmlData::class.java).fromJsonValue(data)
                            ?: throw IllegalStateException("Cannot deserialize ${EJRawHtmlData::class.java.name}")
                    )
                }
            }
        } catch (exception: IllegalArgumentException) {
            EJKit.getCustomBlocks().find { it.type.jsonName == type }?.let {
                val ejBlockData = moshi.adapter(it.data)
                    .fromJsonValue(data) ?: throw IllegalStateException("Cannot deserialize ${it.data.name}")

                return EJCustomBlock(
                    it.type,
                    ejBlockData
                )
            }

            exception.printStackTrace()
        }

        return null
    }

    @ToJson
    fun toJson(block: EJBlock): String? {
        TODO("Not implemented")
    }
}