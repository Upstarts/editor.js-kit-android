package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_image.view.*
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.environment.loadImage
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJImageBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class ImageAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<EJBlock>>() {
    override fun isForViewType(items: MutableList<EJBlock>, position: Int): Boolean {
        return items[position].type == EJBlockType.IMAGE
    }

    override fun onBindViewHolder(
        items: MutableList<EJBlock>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as ViewHolder).bind(items[position] as EJImageBlock)


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_image)
        return ViewHolder(view)
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var imageBlock: EJImageBlock

        fun bind(paragraphBlock: EJImageBlock) {
            this.imageBlock = paragraphBlock
            with(itemView) {
                val data = paragraphBlock.data
                theme?.applyImageStyle(imageView, data)
                imageView.loadImage(data.file.url, data)
            }
        }
    }
}