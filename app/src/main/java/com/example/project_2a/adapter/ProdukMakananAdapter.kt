package com.example.project_2a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_2a.databinding.ItemMakananBinding
import com.example.project_2a.model.ProdukModel
import com.squareup.picasso.Picasso

class ProdukMakananAdapter (
	val result: ArrayList<ProdukModel.DataKategoriMakanan>
) : RecyclerView.Adapter<ProdukMakananAdapter.ViewHolder>() {

	lateinit var binding: ItemMakananBinding

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ProdukMakananAdapter.ViewHolder {
		binding = ItemMakananBinding.inflate(
			LayoutInflater.from(parent.context),
			parent, false
		)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ProdukMakananAdapter.ViewHolder, position: Int) {
		val results = result[position]
		Picasso.get().load(results.image).into(holder.imgGambar)
		holder.tvNamaProduk.text = results.nama
		holder.tvRating.text = results.rating
		holder.tvJarak.text = results.jarak

	}

	override fun getItemCount() = result.size
	class ViewHolder(binding: ItemMakananBinding) : RecyclerView.ViewHolder(binding.root) {
		//Inisialisasi
		val imgGambar = binding.imgGambar
		val tvNamaProduk = binding.tvNamaProduk
		val tvRating = binding.tvRating
		val tvJarak = binding.tvJarak
	}
}
