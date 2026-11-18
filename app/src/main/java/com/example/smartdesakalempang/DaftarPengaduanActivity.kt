package com.example.smartdesakalempang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class DaftarPengaduanActivity : AppCompatActivity() {

    private lateinit var adapter: PengaduanAdapter
    private lateinit var repository: PengaduanRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pengaduan)

        supportActionBar?.title = "Daftar Pengaduan Desa Kalempang"

        // Inisialisasi
        repository = PengaduanRepository(application)
        val rvPengaduan = findViewById<RecyclerView>(R.id.rvPengaduan)
        rvPengaduan.layoutManager = LinearLayoutManager(this)

        // Buat Adapter + aksi klik item
        adapter = PengaduanAdapter { pengaduan ->
            val intent = Intent(this, FormPengaduanActivity::class.java)
            intent.putExtra("ID", pengaduan.id)
            intent.putExtra("JUDUL", pengaduan.judul)
            intent.putExtra("NAMA", pengaduan.namaPelapor)
            intent.putExtra("KONTAK", pengaduan.kontak)
            intent.putExtra("ISI", pengaduan.isi)
            intent.putExtra("STATUS", pengaduan.status)
            startActivity(intent)
        }

        // Pasang adapter ke tampilan
        rvPengaduan.adapter = adapter

        // Ambil data dari database secara otomatis
        lifecycleScope.launch {
            repository.daftarPengaduan.collect { daftarBaru ->
                adapter.perbaruiData(daftarBaru)
            }
        }

        // Tombol tambah pengaduan baru
        val btnTambah = findViewById<FloatingActionButton>(R.id.btnTambah)
        btnTambah.setOnClickListener {
            startActivity(Intent(this, FormPengaduanActivity::class.java))
        }
    }
}