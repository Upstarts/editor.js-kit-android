package work.upstarts.editorjskit.environment

import android.content.Context
import android.content.res.Resources
import android.text.Spannable
import android.text.TextPaint
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.models.data.EJImageData
import work.upstarts.editorjskit.ui.theme.EJStyle

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadImage(url: String?, data: EJImageData) {
    url?.let {
        Glide.with(context)
            .load(url)
            .apply {
                if (data.stretched) {
                    centerCrop()
                }
                val imageHeight = data.file.height
                val imageWidth = data.file.width
                if (imageHeight != null && imageWidth != null) {
                    apply(RequestOptions().override(imageWidth, imageHeight))
                }
            }
            .centerInside()
            .into(this)
    }
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Spannable.applyThemeForUrlSpans(theme: EJStyle?, context: Context) {
    for (u in getSpans(0, length, URLSpan::class.java)) {
        setSpan(object : UnderlineSpan() {
            override fun updateDrawState(tp: TextPaint) {
                tp.isUnderlineText = false //todo make it configurable from theme
                tp.color = theme?.linkColor ?:
                        ContextCompat.getColor(context, R.color.link_color)
            }
        }, getSpanStart(u), getSpanEnd(u), 0)
    }
}
