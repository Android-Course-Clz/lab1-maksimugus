package ru.mchernyaev.lab1

import android.os.Bundle
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.topBackground)
        Glide.with(this)
            .load("https://clck.ru/3GmQLw")
            .placeholder(R.drawable.background_placeholder)
            .into(imageView)
    }
}
