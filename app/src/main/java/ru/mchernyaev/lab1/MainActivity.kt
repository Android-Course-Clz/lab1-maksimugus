package ru.mchernyaev.lab1

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var topBackground: ImageView
    private lateinit var avatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topBackground = findViewById(R.id.topBackground)
        Glide.with(this)
            .load("https://clck.ru/3GmQLw")
            .placeholder(R.drawable.background_placeholder)
            .into(topBackground)

        avatar = findViewById(R.id.avatar)
        Glide.with(this)
            .load("https://clck.ru/3Gp95p")
            .placeholder(R.drawable.avatar_placeholder)
            .into(avatar)


    }
}
