package com.example.project_2a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_2a.databinding.ItemProdukBinding
import com.example.project_2a.model.ProdukModel
import com.squareup.picasso.Picasso

class ProdukCategoriAdapter(
	val result: ArrayList<ProdukModel.DataKategoriProduk>
) :RecyclerView.Adapter<ProdukCategoriAdapter.ViewHolder>() {

	lateinit var binding: ItemProdukBinding

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ProdukCategoriAdapter.ViewHolder {
		binding = ItemProdukBinding.inflate(LayoutInflater.from(parent.context),
			parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ProdukCategoriAdapter.ViewHolder, position: Int) {
		val results = result[position]
		Picasso.get().load(results.image).into(holder.imgGambar)
		holder.tvNamaProduk.text = results.nama
		holder.tvHarga.text = results.harga
		holder.tvSeller.text = results.seller
		holder.tvRating.text = results.rating
		holder.tvTerjual.text = results.terjual
		holder.tvLokasi.text = results.lokasi

	}

	override fun getItemCount() = result.size
	class ViewHolder(binding: ItemProdukBinding)
		:RecyclerView.ViewHolder(binding.root){
		//Inisialisasi
		val imgGambar = binding.imgGambar
		val tvNamaProduk = binding.tvNamaProduk
		val tvHarga = binding.tvHarga
		val tvSeller = binding.tvSeller
		val tvRating = binding.tvRating
		val tvTerjual = binding.tvTerjual
		val tvLokasi = binding.tvLokasi
	}
}
