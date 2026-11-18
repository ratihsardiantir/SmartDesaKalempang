package com.example.smartdesakalempang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PengaduanAdapter(
    private val onItemClick: (Pengaduan) -> Unit
) : RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder>() {

    private var daftar = emptyList<Pengaduan>()

    class PengaduanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvIsiSingkat: TextView = view.findViewById(R.id.tvIsiSingkat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengaduanViewHolder {
        val tampilan = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pengaduan, parent, false)
        return PengaduanViewHolder(tampilan)
    }

    override fun onBindViewHolder(holder: PengaduanViewHolder, posisi: Int) {
        val data = daftar[posisi]
        holder.tvJudul.text = data.judul
        holder.tvStatus.text = data.status
        holder.tvTanggal.text = data.tanggal
        holder.tvIsiSingkat.text = data.isi

        holder.itemView.setOnClickListener {
            onItemClick(data)
        }
    }

    override fun getItemCount() = daftar.size

    fun perbaruiData(daftarBaru: List<Pengaduan>) {
        daftar = daftarBaru
        notifyDataSetChanged()
    }
}