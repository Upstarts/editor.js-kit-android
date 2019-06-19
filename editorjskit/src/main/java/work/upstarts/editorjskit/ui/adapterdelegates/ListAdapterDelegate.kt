package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_list.view.*
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
) : AdapterDelegate<MutableList<EJBlock>>() {
    override fun isForViewType(items: MutableList<EJBlock>, position: Int): Boolean {
        return items[position].type == EJBlockType.LIST
    }

    override fun onBindViewHolder(
        items: MutableList<EJBlock>,
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

        fun bind(listBlock: EJListBlock) {
            this.listData = listBlock.data
            itemView.container.removeAllViews()

            listData.items.forEachIndexed { index, s ->
                if (listData.type == ListType.ORDERED)
                    prefix = "${index + 1}."
                inflateSectionTitle(s, itemView, prefix)
            }
        }
    }

    private fun inflateSectionTitle(text: String, itemView: View, prefix: String) {
        itemView.container.inflate(R.layout.item_list_row).also {
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