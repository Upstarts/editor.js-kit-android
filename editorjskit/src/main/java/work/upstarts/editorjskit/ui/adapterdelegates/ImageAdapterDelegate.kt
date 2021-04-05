package work.upstarts.editorjskit.ui.adapterdelegates

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.environment.inflate
import work.upstarts.editorjskit.environment.loadImage
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJImageBlock
import work.upstarts.editorjskit.ui.theme.EJStyle

class ImageAdapterDelegate(
    private val theme: EJStyle? = null
) : AdapterDelegate<MutableList<Any>>() {
    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is EJBlock && (items[position] as EJBlock).type == EJBlockType.IMAGE
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
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

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val imageCaption: TextView = view.findViewById(R.id.imageCaption)

        fun bind(paragraphBlock: EJImageBlock) {
            this.imageBlock = paragraphBlock
            with(itemView) {
                val data = paragraphBlock.data
                theme?.applyImageStyle(this, data)
                imageView.loadImage(data.file.url, data)
                val caption = data.caption
                if (caption != null && caption.isNotEmpty()) {
                    imageCaption.text = caption
                    imageCaption.visibility = View.VISIBLE
                }
            }
        }
    }
}