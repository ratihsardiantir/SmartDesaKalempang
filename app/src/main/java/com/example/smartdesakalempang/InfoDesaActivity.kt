package com.example.smartdesakalempang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InfoDesaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_desa)
        supportActionBar?.title = "Informasi Desa Kalempang"
    }
}