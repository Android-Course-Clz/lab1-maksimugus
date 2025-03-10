package ru.mchernyaev.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val header = Header(
            "https://clck.ru/3GmQLw",
            "Кре",
            "https://clck.ru/3Gp95p",
            "Геральт улыбается",
            "Геральт Ривийский",
            33,
            33
        )

        val posts = postsList()

        val headerAdapter = HeaderAdapter(header, posts.size)
        val postsAdapter = PostsAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, postsAdapter)
        postsAdapter.submitList(posts)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = concatAdapter
    }
}
