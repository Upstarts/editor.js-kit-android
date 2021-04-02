package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.ui.theme.EJStyle

class DividerAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {
    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.DELIMITER
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind()


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_divider)
        val dividerView: TextView = view.findViewById(R.id.ejDivider)

        theme?.applyDividerStyle(dividerView, view)
        return ViewHolder(view)
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
        }
    }
}