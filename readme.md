
<p align="center">
<picture width=400>
  <source media="(prefers-color-scheme: dark)" srcset="https://static.upstarts.work/ejkit/logo-dark.png?">
  <img src="https://static.upstarts.work/ejkit/logo-light.png?" width=400>
</picture>
</p>

[ ![Download](https://api.bintray.com/packages/heckslam/EditorJSKit/ejkit/images/download.svg) ](https://bintray.com/heckslam/EditorJSKit/ejkit/_latestVersion)

## About

A non-official Android Framework for [Editor.js](https://editorjs.io) - block styled editor. It's purpose to make easy use of rendering and parsing of blocks.

Converts clean json blocks data like [this](app/src/main/assets/dummy_data.json) into native views like that 👇

<p align="center">
  <img src="https://static.upstarts.work/ejkit/editorjs.kit-android-scr.png?" width=420 />
</p>

#### Supported blocks
* 🎩 Header
* 🥑 Raw HTML
* 📷 Image
* 🖌 Delimiter
* 💌 Paragraph
* 🌿 List
* 📋 Table

## Installation

```
maven { url "https://dl.bintray.com/heckslam/EditorJSKit" }
implementation 'com.github.upstarts:ejkit:X.X.X' - look at badge above for latest version
implementation 'com.github.upstarts:ejkit-gson:X.X.X' - adds GSON adapter. If you use other library for parsing json, you need to write adapter yourself.
```

## Setup
 1. Create adapter instance in your activity/fragment (Style parameter is optional)
 ```
    private val rvAdapter: EditorJsAdapter by lazy {
        EditorJsAdapter(EJStyle.create(this.applicationContext))
    }
```
 2. Set adapter to recyclerview in your layout
 3. Init gson with custom Deserializer
```
    val blocksType = object : TypeToken<MutableList<EJBlock>>() {}.type
    val gson = GsonBuilder()
        .registerTypeAdapter(blocksType, EJDeserializer())
        .create()
```
 4. Apply received data to adapter
```
    rvAdapter.items = ejResponse.blocks
```

## Customizing
You can set style globally via `EJKit.style = ...` for all adapters, or pass `EJStyle` instance for specific adapter.
```
 EJStyle.builderWithDefaults(applicationContext)
                .linkColor(ContextCompat.getColor(this,R.color.default_color))
                .blockMargin(10)
                .dividerMargin(10, 10)
                .headingMargin(10, 10, HeadingLevel.h1)
                .imageMargin(10, 10)
                .paragraphMargin(10, 10)
                .rawHtmlMargin(10, 10)
                .tableMargin(10, 10)
                .listItemColor(ContextCompat.getColor(this,R.color.default_color))
                .listBulletColor(ContextCompat.getColor(this,R.color.default_color))
                .bulletSize(6, 6)
                .bulletMargin(10, 10, 10, 10)
                .listTextItemMargin(10, 10, 10, 10)
                .listTextItemTypeFace(ResourcesCompat.getFont(this, R.font.montserrat_bold)!!)
                .listTextItemTextSize(18f)
                .bulletDrawableRes(R.drawable.list_circle)
                .paragraphTextColor(ContextCompat.getColor(this,R.color.default_color))
                .paragraphBackgroundColor(ContextCompat.getColor(this,R.color.default_color))
                .paragraphTypeface(ResourcesCompat.getFont(this, R.font.my_font)!!)
                .paragraphTextColor(ContextCompat.getColor(this,R.color.default_color))
                .headingTypeface(ResourcesCompat.getFont(this, R.font.my_font)!!)
                .headingTypefaceDetailed(ResourcesCompat.getFont(this, R.font.my_font)!!, HeadingLevel.h1)
                .headingFontStyleDetailed(R.style.ParagraphText, HeadingLevel.h1)
                .headingColorDetailed(R.color.default_color, HeadingLevel.h1)
                .headingTextSizes(floatArrayOf( 12f, 12f, 12f,12f,12f,12f))
                .dividerBreakColor(ContextCompat.getColor(this,R.color.default_color))
                .dividerBreakHeight(10)	
                .tableColumnDrawableRes(R.drawable.table_content_cell_bg)
                .tableColumnTextColor(ContextCompat.getColor(this,R.color.default_color))
                .imageBackgroundRes(R.drawable.image_background)
                .imageBorderRes(R.drawable.image_background)
                .build()
```

### Override android style in styles.xml
```
<style name="HeaderText" parent="@android:style/TextAppearance">
    <item name="android:textColor">@color/colorPrimary</item>
</style>
<style name="ParagraphText" parent="@android:style/TextAppearance">
    <item name="android:textSize">16sp</item>
</style>
```

### Create custom block
If you have block that library does not provide currently, you can add it by yourself.
1. Create custom block type and extend it from `EJAbstractBlockType`
```
enum class CustomBlockType(override val jsonName: String) : EJAbstractBlockType {
    TABLE("table")
}
```
2. Create data class for block
```
data class EJTableData(
    val content: List<List<String>>
): EJData()
```
3. And register it
`EJKit.register(EJCustomBlock(CustomB.TABLE, EJTableData::class.java))
`

## Example

You can find and test the example in a [sample activity](app/src/main/java/work/upstarts/MainActivity.kt)

## Author

[Upstarts team](https://upstarts.work)

[Ruslan Aliev](https://github.com/heckslam) - Architecture, Implementation

[Vadim Popov](https://t.me/popovvadim) - Architecture draft