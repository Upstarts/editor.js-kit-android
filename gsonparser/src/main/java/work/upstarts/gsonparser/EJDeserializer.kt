package work.upstarts.gsonparser

import com.google.gson.*
import work.upstarts.editorjskit.EJKit
import work.upstarts.editorjskit.models.*
import work.upstarts.editorjskit.models.data.EJDelimiterData
import work.upstarts.editorjskit.models.data.EJListData
import work.upstarts.editorjskit.models.data.ListType
import java.lang.reflect.Type

class EJDeserializer : JsonDeserializer<MutableList<EJBlock>> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): MutableList<EJBlock> {

        val resultBlockList = mutableListOf<EJBlock>()

        val blocks = json.asJsonArray

        for (block in blocks) {
            val blockObj = block.asJsonObject
            val type = blockObj.get("type").asString
            val data = blockObj.get("data").asJsonObject

            try {
                val typeClass = EJBlockType.fromString(type)

                typeClass.let {
                    val parsedBlock = when (typeClass) {
                        EJBlockType.IMAGE -> EJImageBlock(it, fromJson(data))
                        EJBlockType.DELIMITER -> EJDelimiterBlock(it, EJDelimiterData())
                        EJBlockType.PARAGRAPH -> EJParagraphBlock(it, fromJson(data))
                        EJBlockType.HEADER -> EJHeaderBlock(it, fromJson(data))
                        EJBlockType.LIST -> EJListBlock(
                            it,
                            EJListData(
                                ListType.fromStyle(data["style"].asString),
                                data["items"].asJsonArray.map { item -> item.asString }
                            )
                        )
                        EJBlockType.RAW_HTML -> EJRawHtmlBlock(it, fromJson(data))
                        EJBlockType.TABLE -> EJTableBlock(it, fromJson(data))
                    }

                    resultBlockList.add(parsedBlock)
                }
            } catch (e: IllegalArgumentException) {
                EJKit.getCustomBlocks().find { it.type.jsonName == type }?.let {
                    val parsedData = Gson().fromJson(data, it.data)
                    resultBlockList.add(EJCustomBlock(it.type, parsedData))
                }
                e.printStackTrace()
            }

        }

        return resultBlockList

    }
}

inline fun <reified T> fromJson(value: JsonObject): T {
    return Gson().fromJson(value, T::class.java)
}
