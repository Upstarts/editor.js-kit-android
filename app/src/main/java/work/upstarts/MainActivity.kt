package work.upstarts

import android.content.res.AssetManager
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.HeadingLevel
import work.upstarts.editorjskit.models.serializer.EJDeserializer
import work.upstarts.editorjskit.ui.EditorJsAdapter
import work.upstarts.editorjskit.ui.theme.EJStyle


class MainActivity : AppCompatActivity() {

    private val rvAdapter: EditorJsAdapter by lazy {
        EditorJsAdapter(
            EJStyle.builderWithDefaults(applicationContext)
               /* .headingTypeface(
                    ResourcesCompat.getFont(this, R.font.alice)!!
                )*/
                .headingTypefaceDetailed(
                    Typeface.create("sans-serif-medium", Typeface.BOLD),
                    HeadingLevel.h1
                )
               //  .headingFontStyleDetailed(Typeface.BOLD, HeadingLevel.h1)
                .thematicBreakMargin(100, 100)
                .headingMargin(10, 10, HeadingLevel.h3)
                .paragraphMargin(10, 10)
                .rawHtmlMargin(10, 10)
                .tableMargin(10, 10)
                .build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        val blocksType = object : TypeToken<MutableList<EJBlock>>() {}.type

        val gson = GsonBuilder()
            .registerTypeAdapter(blocksType, EJDeserializer())
            .create()

        val dummyData = readFileFromAssets("dummy_data.json", assets)
        val ejResponse = gson.fromJson(dummyData, EJResponse::class.java)

        rvAdapter.items = ejResponse.blocks

    }
}

fun readFileFromAssets(fname: String, assetsManager: AssetManager): String {
    return assetsManager.open(fname).readBytes().toString(Charsets.UTF_8)
}

data class EJResponse(val blocks: List<EJBlock>)