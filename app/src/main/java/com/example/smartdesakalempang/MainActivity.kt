package com.example.smartdesakalempang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<androidx.cardview.widget.CardView>(R.id.cardPengaduan).setOnClickListener {
            startActivity(Intent(this, DaftarPengaduanActivity::class.java))
        }
    }

    fun bukaInfoDesa(view: android.view.View) {
        startActivity(Intent(this, InfoDesaActivity::class.java))
    }

    fun bukaLayanan(view: android.view.View) {
        startActivity(Intent(this, LayananDesaActivity::class.java))
    }
}