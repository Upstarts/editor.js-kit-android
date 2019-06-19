package work.upstarts.editorjskit.models.data

data class EJListData(
    val type: ListType,
    val items: List<String>
): EJData()

enum class ListType(private val jsonName: String) {
    UNORDERED("unordered"),
    ORDERED("ordered");

    override fun toString() = jsonName
}