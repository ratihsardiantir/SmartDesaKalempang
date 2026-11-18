package com.example.smartdesakalempang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LayananDesaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan_desa)
        supportActionBar?.title = "Layanan Desa Kalempang"
    }
}