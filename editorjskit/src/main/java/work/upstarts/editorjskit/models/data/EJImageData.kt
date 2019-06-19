package work.upstarts.editorjskit.models.data


data class EJImageData(
    val file: ImageFile,
    val withBorder: Boolean,
    val stretched: Boolean,
    val withBackground: Boolean
): EJData()

data class ImageFile(
    val url: String
)