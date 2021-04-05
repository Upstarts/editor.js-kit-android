package work.upstarts.moshiparser

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.models.EJBlockType
import work.upstarts.editorjskit.models.EJParagraphBlock

class EJBlockAdapterTest {

    @Test
    fun fromJsonWithStandardPolymorphicAdapterSingleParagraph() {
        val moshi = Moshi.Builder()
            .add(EJAbstractBlockTypeAdapter())
            .add(
                PolymorphicJsonAdapterFactory.of(EJBlock::class.java, "type")
                    .withSubtype(EJParagraphBlock::class.java, EJBlockType.PARAGRAPH.jsonName)
            )
            .add(KotlinJsonAdapterFactory())
            .build()

        val result = moshi.adapter(EJBlock::class.java)
            .fromJson(singleParagraph)

        instanceOf<EJParagraphBlock>(EJParagraphBlock::class.java).matches(result)
    }

    @Test
    fun fromJsonWithEJBlockAdapterSingleParagraph() {
        val innerMoshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val moshi = Moshi.Builder()
            .add(EJBlockAdapter(innerMoshi))
            .build()

        val result = moshi.adapter(EJBlock::class.java)
            .fromJson(singleParagraph)

        instanceOf<EJParagraphBlock>(EJParagraphBlock::class.java).matches(result)
    }

    companion object {
        const val singleParagraph = "{\"type\": \"paragraph\",\"data\": {\"text\": \"Lorem ipsum bla bla\" } }"
    }
}