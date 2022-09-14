package com.realityexpander.gifapp

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO)  {
            val source = ImageDecoder.createSource(
                resources, R.drawable.kotlin_android
            )
            val drawable = ImageDecoder.decodeDrawable(source)

            val imageView = findViewById<ImageView>(R.id.image_view)
            imageView.post {
                imageView.setImageDrawable(drawable)
                (drawable as? AnimatedImageDrawable)?.start()
            }
        }
    }
}