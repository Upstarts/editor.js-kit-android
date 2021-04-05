package work.upstarts.editorjskit.ui.adapterdelegates

import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.applyThemeForUrlSpans
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJRawHtmlBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class RawHtmlAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.RAW_HTML
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJRawHtmlBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_paragraph)

        val paragraphTextView: TextView = view.findViewById(R.id.paragraphTv)
        theme?.let {
            paragraphTextView.apply {
                it.applyParagraphStyle(this, DEFAULT_MARGIN_HTML)
            }
        }

        return ViewHolder(view)
    }


    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var rawHtmlBlock: EJRawHtmlBlock

        private val paragraphTextView: TextView = view.findViewById(R.id.paragraphTv)

        fun bind(rawHtmlBlock: EJRawHtmlBlock) {
            this.rawHtmlBlock = rawHtmlBlock
            with(itemView) {
                val text = rawHtmlBlock.data.html.parseAsHtml() as Spannable
                text.applyThemeForUrlSpans(theme, context)
                paragraphTextView.text = text
                paragraphTextView.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }
}

const val DEFAULT_MARGIN_HTML = 16