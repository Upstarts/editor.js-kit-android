package work.upstarts.editorjskit.ui

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import work.upstarts.editorjskit.EJKit
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.ui.adapterdelegates.*
import work.upstarts.editorjskit.ui.theme.EJStyle


class EditorJsAdapter(style: EJStyle? = EJKit.ejStyle) : AsyncListDifferDelegationAdapter<EJBlock>(DIFF_CALLBACK) {
    init {
        initDelegates(style)
    }

    private fun initDelegates(style: EJStyle?) {
        delegatesManager
            .addDelegate(HeaderAdapterDelegate(style))
            .addDelegate(ParagraphAdapterDelegate(style))
            .addDelegate(DividerAdapterDelegate(style))
            .addDelegate(ImageAdapterDelegate(style))
            .addDelegate(ListAdapterDelegate(style))
            .addDelegate(TableAdapterDelegate(style))
            .addDelegate(RawHtmlAdapterDelegate(style))
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EJBlock>() {

    override fun areItemsTheSame(oldItem: EJBlock, newItem: EJBlock): Boolean {
        return oldItem.data == newItem.data
    }

    override fun areContentsTheSame(oldItem: EJBlock, newItem: EJBlock): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}