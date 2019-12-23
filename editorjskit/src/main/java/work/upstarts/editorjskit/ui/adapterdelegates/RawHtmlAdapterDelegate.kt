package work.upstarts.editorjskit.ui.adapterdelegates

import android.text.Html
import android.text.Spannable
import android.text.TextPaint
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_paragraph.view.*
import work.upstarts.editorjskit.R
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

        theme?.let {
            view.paragraphTv.apply {
                it.applyParagraphStyle(this, DEFAULT_MARGIN_HTML)
            }
        }

        return ViewHolder(view)
    }


    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var rawHtmlBlock: EJRawHtmlBlock

        fun bind(rawHtmlBlock: EJRawHtmlBlock) {
            this.rawHtmlBlock = rawHtmlBlock
            with(itemView) {
                var text = Html.fromHtml(rawHtmlBlock.data.html) as Spannable
                for (u in text.getSpans(0, text.length, URLSpan::class.java)) {
                    text.setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(tp: TextPaint) {
                            tp.isUnderlineText = false
                            tp.color = theme?.linkColor ?: resources.getColor(R.color.link_color)
                        }
                    }, text.getSpanStart(u), text.getSpanEnd(u), 0)
                }
                paragraphTv.text = text
            }

        }
    }
}

const val DEFAULT_MARGIN_HTML = 16