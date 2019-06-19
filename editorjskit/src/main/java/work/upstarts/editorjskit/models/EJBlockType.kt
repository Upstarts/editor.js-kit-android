package work.upstarts.editorjskit.models

interface EJAbstractBlockType {
    val jsonName: String
}

enum class EJBlockType(override val jsonName: String) : EJAbstractBlockType {
    HEADER("header"),
    PARAGRAPH("paragraph"),
    LIST("list"),
    DELIMITER("delimiter"),
    IMAGE("image"),
    TABLE("table"),
    RAW_HTML("rawTool");

    companion object {
        fun fromString(jsonName: String): EJBlockType {
            return when (jsonName){
                "header" -> HEADER
                "paragraph" -> PARAGRAPH
                "list" -> LIST
                "delimiter" -> DELIMITER
                "image" -> IMAGE
                "table" -> TABLE
                "rawTool" -> RAW_HTML
                else -> throw IllegalArgumentException(jsonName)
            }
        }
    }
}