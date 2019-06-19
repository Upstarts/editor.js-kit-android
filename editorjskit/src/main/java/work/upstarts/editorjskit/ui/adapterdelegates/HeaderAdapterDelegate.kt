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
) : AdapterDelegate<MutableList<EJBlock>>() {

    override fun isForViewType(items: MutableList<EJBlock>, position: Int): Boolean {
        return items[position].type == EJBlockType.HEADER
    }

    override fun onBindViewHolder(
        items: MutableList<EJBlock>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJHeaderBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_header)

        applyHeaderTheme(view) { theme, paint ->
            theme.applyHeadingTextStyle(paint)
        }

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
                theme.applyHeadingTextSize(view.headerTv, headerBlock.data.level)
            }
        }
    }
}