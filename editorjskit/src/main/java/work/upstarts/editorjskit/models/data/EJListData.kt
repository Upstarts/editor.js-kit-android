package work.upstarts.editorjskit.models.data

data class EJListData(
    val style: ListType,
    val items: List<String>
): EJData()

enum class ListType(private val jsonName: String) {
    UNORDERED("unordered"),
    ORDERED("ordered");

    override fun toString() = jsonName

    companion object {
        fun fromStyle(style: String) = values().first { it.jsonName == style }
    }

}
