package com.example.cleancodeinc.kotlinnewsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ListNews : AppCompatActivity() {

    var source = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        if (intent != null) {
            source = intent.getStringExtra("source")
            if (!source.isEmpty()) {
                loadNews(source, false)
            }
        }
    }

    private fun loadNews(source: String?, b: Boolean) {

    } // 32 33
}
