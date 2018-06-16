
import android.content.Context
import com.bumptech.glide.*
import com.bumptech.glide.load.DecodeFormat

import com.bumptech.glide.module.GlideModule

class GlideConfiguration : GlideModule {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }


}