package de.datlag.coilifierandroid

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import de.datlag.coilifier.ImageLoader
import de.datlag.coilifier.commons.load

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.image)
        image.load<Drawable>(ImageLoader.create("https://avatars2.githubusercontent.com/u/46448715")) {
            error("LRMz]^i{{is.xue.S4f6{ijFE1WB", image)
            placeholder(R.mipmap.ic_launcher)
            transform(RoundedCorners(150))
        }
    }
}