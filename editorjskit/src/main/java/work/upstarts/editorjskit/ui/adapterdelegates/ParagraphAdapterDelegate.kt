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
import work.upstarts.editorjskit.models.EJParagraphBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class ParagraphAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {
    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.PARAGRAPH
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJParagraphBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_paragraph)

        theme?.let {
            view.apply {
                it.applyParagraphStyle(this, DEFAULT_MARGIN_PARAGRAF)
            }
        }
        return ViewHolder(view)
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var headerBlock: EJParagraphBlock

        private val paragraphTextView: TextView = view.findViewById(R.id.paragraphTv)

        fun bind(paragraphBlock: EJParagraphBlock) {
            this.headerBlock = paragraphBlock
            with(itemView) {
                val text = paragraphBlock.data.text.parseAsHtml() as Spannable
                text.applyThemeForUrlSpans(theme, context)
                paragraphTextView.text = text
                paragraphTextView.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }
}

const val DEFAULT_MARGIN_PARAGRAF = 16