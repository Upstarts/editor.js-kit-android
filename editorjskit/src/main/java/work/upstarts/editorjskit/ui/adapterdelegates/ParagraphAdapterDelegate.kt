package work.upstarts.editorjskit.ui.adapterdelegates

import android.text.method.LinkMovementMethod
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
            view.paragraphTv.apply {
                it.applyParagraphTextStyle(this)
            }
        }

        return ViewHolder(view)
    }


    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var headerBlock: EJParagraphBlock

        fun bind(paragraphBlock: EJParagraphBlock) {
            this.headerBlock = paragraphBlock
            with(itemView) {
                paragraphTv.text = paragraphBlock.data.text.parseAsHtml()
                paragraphTv.movementMethod = LinkMovementMethod()
            }
        }
    }
}