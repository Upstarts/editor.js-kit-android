package work.upstarts.editorjskit.ui.theme

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import work.upstarts.editorjskit.R
import work.upstarts.editorjskit.models.data.EJImageData
import work.upstarts.editorjskit.ui.views.HeaderTextView
import java.util.*

/**
 * Class to hold *theming* information for rending.
 */
open class EJStyle protected constructor(builder: Builder) {

    protected val linkColor: Int = builder.linkColor

    // used in quote, lists
    val blockPadding: Int = builder.blockMargin

    // by default uses text color (applied for un-ordered lists & ordered (bullets & numbers)
    protected val listItemColor: Int = builder.listItemColor
    protected val listBulletColor: Int = builder.listBulletColor

    protected val bulletDrawable: Int = builder.bulletDrawableRes

    // by default - main text color
    protected val paragraphTextColor: Int = builder.paragraphTextColor

    protected val paragraphBackgroundColor: Int = builder.paragraphBackgroundColor

    // by default Typeface.MONOSPACE
    protected val paragraphTypeface: Typeface? = builder.paragraphTypeface

    // by default a bit (how much?!) smaller than normal text
    // applied ONLY if default typeface was used, otherwise, not applied
    protected val paragraphTextSize: Int = builder.paragraphTextSize

    // by default, whatever typeface is set on the TextView
    protected val headingTypeface: Typeface? = builder.headingTypeface

    // by default, use standard values (see HEADING_SIZES for values).
    // this library supports 6 heading sizes, so make sure the array you pass here has 6 elements.
    protected val headingTextSizeMultipliers: FloatArray? = builder.headingTextSizes

    protected val delimiterColor: Int = builder.delimiterColor

    protected val delimiterHeight: Int = builder.delimiterBreakHeight

    protected val tableColumnDrawableRes: Int = builder.tableColumnDrawableRes
    protected val tableColumnTextColor: Int = builder.tableColumnTextColor

    protected val imageBackground: Int = builder.imageBackground
    protected val imageBorder: Int = builder.imageBorder

    private fun applyLinkStyle(tv: TextView) {
        if (linkColor != 0) {
            tv.setLinkTextColor(linkColor)
        }
    }

    fun applyTableColumnBackground(view: TextView) {
        if (tableColumnDrawableRes != 0) {
            view.background = view.context.getDrawable(tableColumnDrawableRes)
        }
        if (tableColumnTextColor != 0) {
            view.setTextColor(tableColumnTextColor)
        }
    }

    fun applyListItemStyle(bulletView: View, textView: TextView) {
        if (listItemColor != 0) {
            textView.setTextColor(listItemColor)
            if (listBulletColor != 0) {
                bulletView.background.setTint(listBulletColor)
            }
        }

        if (bulletDrawable != 0) {
            bulletView.background = bulletView.context.getDrawable(bulletDrawable)
        }
    }

    fun applyParagraphTextStyle(textView: TextView) {
        if (paragraphTextColor != 0) {
            textView.setTextColor(paragraphTextColor)
        }

        if (paragraphTypeface != null) {
            textView.typeface = paragraphTypeface
        }

        if (paragraphTextSize > 0) {
            textView.textSize = paragraphTextSize.toFloat()
        }
        applyLinkStyle(textView)
        applyBackgroundColor(textView)

        applyBlockPadding(textView)
    }

    private fun applyBlockPadding(textView: TextView) {
        textView.setPadding(blockPadding)
    }

    private fun applyBackgroundColor(textView: TextView) {
        if (paragraphBackgroundColor != 0) {
            textView.background = ColorDrawable(paragraphBackgroundColor)
        }
    }

    fun applyHeadingTextStyle(paint: Paint) {
        if (headingTypeface == null) {
            paint.isFakeBoldText = true
        } else {
            paint.typeface = headingTypeface
        }
    }

    fun applyHeadingTextSize(headerTv: HeaderTextView, @IntRange(from = 1, to = 6) level: Int) {
        val textSizes = headingTextSizeMultipliers ?: HEADING_SIZES

        if (textSizes.size >= level) {
            headerTv.setHeaderLevel(textSizes[level - 1])
        } else {
            throw IllegalStateException(
                String.format(
                    Locale.US,
                    "Supplied heading level: %d is invalid, where configured heading sizes are: `%s`",
                    level, Arrays.toString(textSizes)
                )
            )
        }
    }

    fun applyThematicBreakStyle(dividerView: View) {
        if (delimiterColor != 0) {
            dividerView.background = ColorDrawable(delimiterColor)
        }

        if (delimiterHeight >= 0) {
            dividerView.updateLayoutParams<FrameLayout.LayoutParams> {
                height = delimiterHeight
            }
        }
    }

    fun applyImageStyle(imageView: ImageView, data: EJImageData) {
        if (imageBackground != 0 && data.withBackground) {
            imageView.background = imageView.context.getDrawable(imageBackground)
        }

        if (imageBorder != 0 && data.withBorder) {
            imageView.background = imageView.context.getDrawable(imageBorder)
        }
    }

    class Builder {

        var linkColor: Int = 0
        var blockMargin: Int = 0
        var listItemColor: Int = 0
        var listBulletColor: Int = 0
        var bulletDrawableRes: Int = 0
        var paragraphTextColor: Int = 0
        var paragraphBackgroundColor: Int = 0
        var paragraphTypeface: Typeface? = null
        var paragraphBlockTypeface: Typeface? = null
        var paragraphTextSize: Int = 0
        var headingTypeface: Typeface? = null
        var headingTextSizes: FloatArray? = null
        var delimiterColor: Int = 0
        var delimiterBreakHeight = -1

        var tableColumnDrawableRes: Int = 0
        var tableColumnTextColor: Int = 0

        var imageBackground: Int = 0
        var imageBorder: Int = 0

        internal constructor() {}

        internal constructor(theme: EJStyle) {
            this.linkColor = theme.linkColor
            this.blockMargin = theme.blockPadding
            this.listItemColor = theme.listItemColor
            this.listBulletColor = theme.listBulletColor
            this.bulletDrawableRes = theme.bulletDrawable
            this.paragraphTextColor = theme.paragraphTextColor
            this.paragraphBackgroundColor = theme.paragraphBackgroundColor
            this.paragraphTypeface = theme.paragraphTypeface
            this.paragraphTextSize = theme.paragraphTextSize
            this.headingTypeface = theme.headingTypeface
            this.headingTextSizes = theme.headingTextSizeMultipliers
            this.delimiterColor = theme.delimiterColor
            this.delimiterBreakHeight = theme.delimiterHeight
            this.tableColumnTextColor = theme.tableColumnTextColor
            this.imageBackground = theme.imageBackground
            this.imageBorder = theme.imageBorder
        }

        fun linkColor(@ColorInt linkColor: Int): Builder {
            this.linkColor = linkColor
            return this
        }

        fun blockMargin(@Px blockMargin: Int): Builder {
            this.blockMargin = blockMargin
            return this
        }


        fun listItemColor(@ColorInt listItemColor: Int): Builder {
            this.listItemColor = listItemColor
            return this
        }

        fun listBulletColor(@ColorInt listItemColor: Int): Builder {
            this.listBulletColor = listItemColor
            return this
        }

        fun bulletDrawableRes(@DrawableRes drawableRes: Int): Builder {
            this.bulletDrawableRes = drawableRes
            return this
        }

        fun imageBackgroundRes(@DrawableRes imageBackground: Int): Builder {
            this.imageBackground = imageBackground
            return this
        }

        fun imageBorderRes(@DrawableRes imageBorder: Int): Builder {
            this.imageBorder = imageBorder
            return this
        }

        fun tableColumnDrawableRes(@DrawableRes drawableRes: Int): Builder {
            this.tableColumnDrawableRes = drawableRes
            return this
        }

        fun tableColumnTextColor(@ColorInt color: Int): Builder {
            this.tableColumnTextColor = color
            return this
        }

        fun paragraphTextColor(@ColorInt paragraphTextColor: Int): Builder {
            this.paragraphTextColor = paragraphTextColor
            return this
        }

        fun paragraphBackgroundColor(@ColorInt paragraphBackgroundColor: Int): Builder {
            this.paragraphBackgroundColor = paragraphBackgroundColor
            return this
        }

        fun paragraphTypeface(paragraphTypeface: Typeface): Builder {
            this.paragraphTypeface = paragraphTypeface
            return this
        }

        fun paragraphBlockTypeface(typeface: Typeface): Builder {
            this.paragraphBlockTypeface = typeface
            return this
        }

        fun paragraphTextSize(@Px paragraphTextSize: Int): Builder {
            this.paragraphTextSize = paragraphTextSize
            return this
        }

        fun headingTypeface(headingTypeface: Typeface): Builder {
            this.headingTypeface = headingTypeface
            return this
        }

        /**
         * @param headingTextSizes an array of multipliers values for heading elements.
         * The base value for this multipliers is TextView\'s text size
         */
        fun headingTextSizes(@Size(6) headingTextSizes: FloatArray): Builder {
            this.headingTextSizes = headingTextSizes
            return this
        }

        fun thematicBreakColor(@ColorInt thematicBreakColor: Int): Builder {
            this.delimiterColor = thematicBreakColor
            return this
        }

        fun thematicBreakHeight(@Px thematicBreakHeight: Int): Builder {
            this.delimiterBreakHeight = thematicBreakHeight
            return this
        }

        fun build(): EJStyle {
            return EJStyle(this)
        }
    }

    companion object {

        /**
         * Factory method to obtain an instance of [EJStyle] with all values as defaults
         *
         * @param context Context in order to resolve defaults
         * @return [EJStyle] instance
         * @see .builderWithDefaults
         * @since 1.0.0
         */
        fun create(context: Context): EJStyle {
            return builderWithDefaults(context).build()
        }

        /**
         * Create an **empty** instance of [Builder] with no default values applied
         */
        fun emptyBuilder(): Builder {
            return Builder()
        }

        /**
         * Factory method to create a [Builder] instance and initialize it with values
         * from supplied [EJStyle]
         *
         * @param copyFrom [EJStyle] to copy values from
         * @return [Builder] instance
         * @see .builderWithDefaults
         * @since 1.0.0
         */
        fun builder(copyFrom: EJStyle): Builder {
            return Builder(copyFrom)
        }

        /**
         * Factory method to obtain a [Builder] instance initialized with default values taken
         * from current application theme.
         *
         * @param context Context to obtain default styling values (colors, etc)
         * @return [Builder] instance
         * @since 1.0.0
         */
        fun builderWithDefaults(context: Context): Builder {
            return with(context) {
                Builder()
                    .bulletDrawableRes(R.drawable.list_circle)
                    .tableColumnDrawableRes(R.drawable.table_content_cell_bg)
                    .imageBackgroundRes(R.drawable.image_background)
            }
        }

        private val HEADING_SIZES = floatArrayOf(32f, 24f, 18f, 16f, 14f, 12f)

    }

}