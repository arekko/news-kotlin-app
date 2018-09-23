package com.example.cleancodeinc.kotlinnewsapp

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.cleancodeinc.kotlinnewsapp.Adapter.ListSourceAdapter
import com.example.cleancodeinc.kotlinnewsapp.Common.Common
import com.example.cleancodeinc.kotlinnewsapp.Interface.NewService
import com.example.cleancodeinc.kotlinnewsapp.Model.WebSite
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mService: NewService
    lateinit var adapter: ListSourceAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Init cache DB

        Paper.init(this)

        // Init services

        mService = Common.newService

        // Init view

        swipe_to_refresh.setOnRefreshListener {
            loadWebsiteSource(true)
        }

        recycler_view_source_news.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler_view_source_news.layoutManager = layoutManager


//        dialog = SpotsDialog.Builder().setContext(this).build()
        dialog = SpotsDialog(this)

        loadWebsiteSource(false)


    }

    private fun loadWebsiteSource(isRefresh: Boolean) {

        if (!isRefresh) {


            val cache = Paper.book().read<String>("cache")
            if (cache != null && !cache.isBlank() && cache != "null") {

                //Read cache

                val webSite = Gson().fromJson<WebSite>(cache, WebSite::class.java)
                adapter = ListSourceAdapter(baseContext, webSite)
                adapter.notifyDataSetChanged()
                recycler_view_source_news.adapter = adapter

            } else {

                //Load website and write cache

                dialog.show()

                // Fetch new data

                mService.sources.enqueue(object : retrofit2.Callback<WebSite> {

                    override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                        adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                        adapter.notifyDataSetChanged()
                        recycler_view_source_news.adapter = adapter

                        //Save to cache

                        Paper.book().write("cache", Gson().toJson(response!!.body()!!))

                        dialog.dismiss()
                    }

                    override fun onFailure(call: Call<WebSite>, t: Throwable) {
                        Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        } else {

            swipe_to_refresh.isRefreshing = true
            //fetch new data


            mService.sources.enqueue(object : retrofit2.Callback<WebSite> {
                override fun onFailure(call: Call<WebSite>, t: Throwable) {
                    Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                    adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                    adapter.notifyDataSetChanged()
                    recycler_view_source_news.adapter = adapter

                    //Save to cache

                    Paper.book().write("cache", Gson().toJson(response!!.body()!!))

                    swipe_to_refresh.isRefreshing = false


                }


            })
        }
    }
}
