package work.upstarts.editorjskit.environment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadImage(url: String?, centerCrop: Boolean) {
    url?.let {
        Glide.with(context)
            .load(url)
            .apply {
                if (centerCrop)
                    centerCrop()
            }
            .into(this)
    }
}