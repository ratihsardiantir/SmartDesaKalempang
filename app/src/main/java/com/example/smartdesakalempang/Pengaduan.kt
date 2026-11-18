package com.example.smartdesakalempang

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengaduan")
data class Pengaduan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isi: String,
    val namaPelapor: String,
    val kontak: String,
    val tanggal: String,
    val status: String = "Menunggu Verifikasi"
)