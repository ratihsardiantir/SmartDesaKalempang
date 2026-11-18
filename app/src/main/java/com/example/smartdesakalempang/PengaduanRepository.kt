package com.example.smartdesakalempang

import android.app.Application
import kotlinx.coroutines.flow.Flow

class PengaduanRepository(application: Application) {
    private val dao = AppDatabase.getInstance(application).pengaduanDao()
    val daftarPengaduan: Flow<List<Pengaduan>> = dao.ambilSemuaPengaduan()

    suspend fun tambah(pengaduan: Pengaduan) = dao.tambahPengaduan(pengaduan)
    suspend fun ubah(pengaduan: Pengaduan) = dao.ubahPengaduan(pengaduan)
    suspend fun hapus(pengaduan: Pengaduan) = dao.hapusPengaduan(pengaduan)
}