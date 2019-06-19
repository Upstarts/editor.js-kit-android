package work.upstarts

import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.serializer.EJDeserializer
import work.upstarts.editorjskit.ui.EditorJsAdapter
import work.upstarts.editorjskit.ui.theme.EJStyle
import com.google.gson.reflect.TypeToken
import work.upstarts.editorjskit.EJKit


class MainActivity : AppCompatActivity() {

    private val rvAdapter: EditorJsAdapter by lazy {
        EditorJsAdapter(EJStyle.create(this.applicationContext))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter

            addItemDecoration(SpacesItemDecoration(
                context.resources.getDimensionPixelSize(R.dimen.recycler_space)
            ))
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