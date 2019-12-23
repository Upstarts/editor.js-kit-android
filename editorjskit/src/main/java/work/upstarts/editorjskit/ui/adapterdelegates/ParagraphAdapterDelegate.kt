package work.upstarts.editorjskit.ui.adapterdelegates

import android.text.Html
import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_paragraph.view.*
import work.upstarts.editorjskit.R
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

        fun bind(paragraphBlock: EJParagraphBlock) {
            this.headerBlock = paragraphBlock
            with(itemView) {
                var text = Html.fromHtml(paragraphBlock.data.text) as Spannable
                for (u in text.getSpans(0, text.length, URLSpan::class.java)) {
                    text.setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(tp: TextPaint) {
                            tp.isUnderlineText = false
                            tp.color = theme?.linkColor ?: resources.getColor(R.color.link_color)
                        }
                    }, text.getSpanStart(u), text.getSpanEnd(u), 0)
                }

                paragraphTv.text = text
                paragraphTv.movementMethod = LinkMovementMethod()
            }
        }
    }
}

const val DEFAULT_MARGIN_PARAGRAF = 16