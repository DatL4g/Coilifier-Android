package de.datlag.coilifierandroid

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import de.datlag.coilifier.PlaceholderScaling
import de.datlag.coilifier.commons.load

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.image)
        image.load<Drawable>("https://avatars2.githubusercontent.com/u/46448715") {
            error(R.mipmap.ic_launcher)
            placeholder(R.mipmap.ic_launcher, PlaceholderScaling.fitCenter())
            transform(RoundedCorners(150))
        }
    }
}