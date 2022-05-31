package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJListBlock
import work.upstarts.editorjskit.models.data.EJListData
import work.upstarts.editorjskit.models.data.ListType
import work.upstarts.editorjskit.ui.theme.EJStyle

class ListAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {
    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.LIST
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJListBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_list)
        return ViewHolder(view)
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var listData: EJListData
        var prefix = ""

        private val containerLayout: LinearLayout = view.findViewById(R.id.container)

        fun bind(listBlock: EJListBlock) {
            this.listData = listBlock.data
            containerLayout.removeAllViews()
            theme?.applyListMargin(itemView)
            listData.items.forEachIndexed { index, s ->
                if (listData.style == ListType.ORDERED)
                    prefix = "${index + 1}."
                inflateSectionTitle(s, itemView, prefix)
            }
        }

        private fun inflateSectionTitle(text: String, itemView: View, prefix: String) {
            containerLayout.inflate(R.layout.item_list_row).also {
                it.findViewWithTag<TextView>("listRowTv").apply {
                    tag = null
                    val listText = "$prefix $text"
                    setText(listText)
                    val bulletView = it.findViewById<View>(R.id.bulletView)
                    if (prefix.isEmpty()) {
                        bulletView.visibility = View.VISIBLE
                    }

                    theme?.applyListItemStyle(bulletView, this)
                }

                (itemView as ViewGroup).addView(it)
            }
        }
    }
}
