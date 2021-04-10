package work.upstarts.editorjskit.ui.adapterdelegates

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJHeaderBlock
import work.upstarts.editorjskit.ui.theme.EJStyle
import work.upstarts.editorjskit.ui.views.HeaderTextView

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
            val headerTextView: HeaderTextView = view.findViewById(R.id.headerTv)

            headerTextView.apply {
                headerInitializer(it, paint)
            }
        }
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var headerBlock: EJHeaderBlock
        private val headerTextView: HeaderTextView = view.findViewById(R.id.headerTv)

        fun bind(headerBlock: EJHeaderBlock) {
            this.headerBlock = headerBlock

            headerTextView.text = headerBlock.data.text

            applyHeaderTheme(view) { theme, paint ->
                theme.applyHeadingStyle(view, paint, headerBlock.data.level)
            }
        }
    }
}