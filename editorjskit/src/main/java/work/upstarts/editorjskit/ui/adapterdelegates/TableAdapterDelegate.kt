package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJTableBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class TableAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {
    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.TABLE
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJTableBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_table)

        return ViewHolder(view)
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tableBlock: EJTableBlock

        fun bind(tableBlock: EJTableBlock) {
            this.tableBlock = tableBlock

            (itemView as ViewGroup).removeAllViews()
            tableBlock.data.content.forEach {
                val tableRow = TableRow(itemView.context).apply {
                    layoutParams = TableRow.LayoutParams()
                }

                it.forEach {
                    val column = tableRow.inflate(R.layout.item_column)
                    (column as AppCompatTextView).apply {
                        text = it
                        theme?.applyTableColumnBackground(this)
                    }
                    tableRow.addView(column )
                }

                (itemView as TableLayout).addView(tableRow)
            }
        }
    }
}