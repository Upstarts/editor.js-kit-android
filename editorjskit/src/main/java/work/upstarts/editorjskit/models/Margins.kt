package work.upstarts.editorjskit.models

class Margins {

    var deviderMargin: MarginData? = null
    val headerMargin: HashMap<Int, MarginData>? = HashMap()
    var imageMargin: MarginData? = null
    var listMargin: MarginData? = null
    var paragraphMargin: MarginData? = null
    var rawHtmlMargin: MarginData? = null
    var tableMargin: MarginData? = null
    var bulletMargin: MarginData? = null
    var listTextItemMargin: MarginData? = null

    data class MarginData(val marginLeft: Int, val marginTop: Int, val marginRight: Int, val marginBottom: Int)

    fun setHeaderMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int, headerType: HeadingLevel) {
        headerMargin?.set(headerType.value, MarginData(marginLeft, marginTop, marginRight, marginBottom))
    }

    fun setParagraphMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        paragraphMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setImageMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        imageMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setBulletMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        bulletMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setListTextItemMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        listTextItemMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setListMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        listMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setHtmlMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        rawHtmlMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setTableMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        tableMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }

    fun setDeviderMargin (marginLeft: Int,  marginTop: Int, marginRight: Int, marginBottom: Int) {
        deviderMargin = MarginData(marginLeft, marginTop, marginRight, marginBottom)
    }
}