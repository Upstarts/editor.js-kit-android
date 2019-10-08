package work.upstarts.editorjskit.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class HeaderTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attributeSet, defStyleAttr) {

    private val density = context.resources.displayMetrics.density

    fun setHeaderLevel(level: Float) {
        val headerSize = density * level
        if (paint.textSize != headerSize)
            paint.textSize = headerSize
    }

}