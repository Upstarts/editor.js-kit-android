package work.upstarts.editorjskit.models.data


data class EJImageData(
    val file: ImageFile,
    val caption: String?,
    val withBorder: Boolean,
    val stretched: Boolean,
    val withBackground: Boolean
): EJData()

data class ImageFile(
    val url: String,
    val width: Int?,
    val height: Int?
)