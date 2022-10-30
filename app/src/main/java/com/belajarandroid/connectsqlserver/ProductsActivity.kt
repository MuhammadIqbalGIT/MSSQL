package com.belajarandroid.connectsqlserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {


    lateinit var rv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        rv = findViewById(R.id.rvProducts)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        MainActivity.db.ctx=this
        MainActivity.db.getProducts(rv)
    }
}