package work.upstarts.moshiparser

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import work.upstarts.editorjskit.models.EJAbstractBlockType
import work.upstarts.editorjskit.models.EJBlockType

class EJAbstractBlockTypeAdapter{
    @FromJson
    fun fromJson(type: String): EJAbstractBlockType {
        return EJBlockType.fromString(type)
    }

    @ToJson
    fun toJson(type: EJAbstractBlockType): String {
        return type.jsonName
    }
}