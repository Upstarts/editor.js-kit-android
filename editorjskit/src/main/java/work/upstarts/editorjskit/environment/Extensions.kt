package work.upstarts.editorjskit.environment

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import work.upstarts.editorjskit.models.data.EJImageData

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
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
