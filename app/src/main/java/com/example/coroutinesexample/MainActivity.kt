package com.example.coroutinesexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeRetrofitService = RetrofitFactory.makeRetrofitService()
        GlobalScope.launch {
            val posts = makeRetrofitService.getPosts()
            Log.d(TAG, "onCreate: Successful ${posts.isSuccessful}")
            if (posts.isSuccessful) {
                val body = posts.body()
                if (body != null) {
                    for (post: Post in body) {
                        Log.d(TAG, "onCreate: $post\n")
                    }
                }
            }
        }
    }
}