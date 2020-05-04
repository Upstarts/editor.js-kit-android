package work.upstarts.editorjskit.models

class Margins {

    var deviderMargin: MarginData? = null
    val headerMargin: HashMap<Int, MarginData>? = HashMap()
    var imageMargin: MarginData? = null
    var listMargin: MarginData? = null
    var paragraphMargin: MarginData? = null
    var rawHtmlMargin: MarginData? = null
    var tableMargin: MarginData? = null

    data class MarginData(val marginTop: Int, val marginBottom: Int)

    fun setHeaderMargin(marginTop: Int, marginBottom: Int, headerType: HeadingLevel) {
        headerMargin?.set(headerType.value, MarginData(marginTop, marginBottom))
    }

    fun setParagraphMargin(marginTop: Int, marginBottom: Int) {
        paragraphMargin = MarginData(marginTop, marginBottom)
    }

    fun setImageMargin(marginTop: Int, marginBottom: Int) {
        imageMargin = MarginData(marginTop, marginBottom)
    }

    fun setListMargin(marginTop: Int, marginBottom: Int) {
        listMargin = MarginData(marginTop, marginBottom)
    }

    fun setHtmlMargin(marginTop: Int, marginBottom: Int) {
        rawHtmlMargin = MarginData(marginTop, marginBottom)
    }

    fun setTableMargin(marginTop: Int, marginBottom: Int) {
        tableMargin = MarginData(marginTop, marginBottom)
    }

    fun setDeviderMargin(marginTop: Int, marginBottom: Int) {
        deviderMargin = MarginData(marginTop, marginBottom)
    }
}