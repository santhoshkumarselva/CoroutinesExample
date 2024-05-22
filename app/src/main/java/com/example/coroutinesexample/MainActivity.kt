package com.example.coroutinesexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeRetrofitService = RetrofitFactory.makeRetrofitService()
        var lists = ArrayList<String>()
        GlobalScope.launch {
            val posts = makeRetrofitService.getPosts()
            Log.d(TAG, "onCreate: Successful ${posts.isSuccessful}")
            if (posts.isSuccessful) {
                val body = posts.body()
                if (body != null) {
                    for (post: Post in body) {
                        Log.d(TAG, "onCreate: $post\n")
                        lists.add(post.title)
                    }
                }
            }
            Log.d(TAG, "title : $lists")
        }
    }
}