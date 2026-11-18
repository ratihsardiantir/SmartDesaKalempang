package com.example.smartdesakalempang

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FormPengaduanActivity : AppCompatActivity() {

    private lateinit var repository: PengaduanRepository
    private var idData: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pengaduan)

        repository = PengaduanRepository(application)

        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etKontak = findViewById<EditText>(R.id.etKontak)
        val etIsi = findViewById<EditText>(R.id.etIsi)
        val btnSimpan = findViewById<android.widget.Button>(R.id.btnSimpan)
        val btnHapus = findViewById<android.widget.Button>(R.id.btnHapus)

        // Cek jika ada data yang dikirim (mode edit)
        if (intent.hasExtra("ID")) {
            idData = intent.getIntExtra("ID", 0)
            etJudul.setText(intent.getStringExtra("JUDUL"))
            etNama.setText(intent.getStringExtra("NAMA"))
            etKontak.setText(intent.getStringExtra("KONTAK"))
            etIsi.setText(intent.getStringExtra("ISI"))
            btnHapus.visibility = android.view.View.VISIBLE
            supportActionBar?.title = "Ubah Pengaduan"
        } else {
            supportActionBar?.title = "Tambah Pengaduan Baru"
        }

        // Aksi tombol simpan
        btnSimpan.setOnClickListener {
            val judul = etJudul.text.toString().trim()
            val nama = etNama.text.toString().trim()
            val kontak = etKontak.text.toString().trim()
            val isi = etIsi.text.toString().trim()

            if (judul.isEmpty() || nama.isEmpty() || kontak.isEmpty() || isi.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tanggal = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault()).format(System.currentTimeMillis())
            val status = intent.getStringExtra("STATUS") ?: "Menunggu Verifikasi"

            val pengaduan = Pengaduan(
                id = idData ?: 0,
                judul = judul,
                namaPelapor = nama,
                kontak = kontak,
                isi = isi,
                tanggal = tanggal,
                status = status
            )

            CoroutineScope(Dispatchers.IO).launch {
                if (idData == null) {
                    repository.tambah(pengaduan)
                    runOnUiThread {
                        Toast.makeText(this@FormPengaduanActivity, "Pengaduan berhasil disimpan", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    repository.ubah(pengaduan)
                    runOnUiThread {
                        Toast.makeText(this@FormPengaduanActivity, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }

        // Aksi tombol hapus
        btnHapus.setOnClickListener {
            idData?.let {
                val pengaduan = Pengaduan(
                    id = it,
                    judul = etJudul.text.toString(),
                    namaPelapor = etNama.text.toString(),
                    kontak = etKontak.text.toString(),
                    isi = etIsi.text.toString(),
                    tanggal = "",
                    status = ""
                )

                CoroutineScope(Dispatchers.IO).launch {
                    repository.hapus(pengaduan)
                    runOnUiThread {
                        Toast.makeText(this@FormPengaduanActivity, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}