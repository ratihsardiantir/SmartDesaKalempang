package com.example.smartdesakalempang

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PengaduanDao {
    @Query("SELECT * FROM pengaduan ORDER BY id DESC")
    fun ambilSemuaPengaduan(): Flow<List<Pengaduan>>

    @Insert
    suspend fun tambahPengaduan(pengaduan: Pengaduan)

    @Update
    suspend fun ubahPengaduan(pengaduan: Pengaduan)

    @Delete
    suspend fun hapusPengaduan(pengaduan: Pengaduan)
}