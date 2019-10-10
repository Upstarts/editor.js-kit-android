package work.upstarts.editorjskit.ui.adapterdelegates

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_header.view.*
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJHeaderBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class HeaderAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {

    var items: MutableList<Any>? = null

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.HEADER
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJHeaderBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_header)
        return ViewHolder(view)
    }

    fun applyHeaderTheme(view: View, headerInitializer: (EJStyle, Paint) -> Unit) {
        theme?.let {
            view.headerTv.apply {
                headerInitializer(it, paint)
            }
        }
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var headerBlock: EJHeaderBlock

        fun bind(headerBlock: EJHeaderBlock) {
            this.headerBlock = headerBlock

            with(itemView) {
                headerTv.text = headerBlock.data.text
            }

            applyHeaderTheme(view) { theme, paint ->
                theme.applyHeadingTextStyle(paint, headerBlock.data.level)
                theme.applyHeadingTextSize(view.headerTv, headerBlock.data.level)
                theme.applyHeadingMargin(view, headerBlock.data.level)
                theme.applyFontStyle(view.headerTv, headerBlock.data.level)
            }
        }
    }
}