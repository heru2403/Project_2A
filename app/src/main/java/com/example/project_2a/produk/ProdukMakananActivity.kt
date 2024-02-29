package com.example.project_2a.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.project_2a.R
import com.example.project_2a.adapter.ProdukCategoriAdapter
import com.example.project_2a.adapter.ProdukMakananAdapter
import com.example.project_2a.config.Network
import com.example.project_2a.databinding.ActivityProdukKategoriBinding
import com.example.project_2a.databinding.ActivityProdukMakananBinding
import com.example.project_2a.model.ProdukModel
import retrofit2.Call
import retrofit2.Response

class ProdukMakananActivity : AppCompatActivity() {
	private lateinit var binding: ActivityProdukMakananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		binding = ActivityProdukMakananBinding.inflate(layoutInflater)
		setContentView(binding.root)
    }

	override fun onResume(){
		super.onResume()
		cari()
		getApiKategoriMakanan("1","")
	}

	fun getApiKategoriMakanan(id_makanan: String, filter: String){
		val kategoriMakanan = Network.service().getProdukMakananKategori(id_makanan, filter)
		kategoriMakanan.enqueue(object: retrofit2.Callback<ProdukModel>{
			override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
				if(response.isSuccessful){
					val hasil = response.body()
					if(hasil!!.success==1){
						binding.imgNotFound.visibility = View.GONE
						binding.rvMakanan.visibility = View.VISIBLE
						//tampilkan isi dari web API ke Adapter
						val produkMakananAdapter = ProdukMakananAdapter(hasil.list_makanan)
						binding.rvMakanan.adapter = produkMakananAdapter
					} else{
						binding.imgNotFound.visibility = View.VISIBLE
						binding.rvMakanan.visibility = View.GONE
					}
				} else{
					Toast.makeText(this@ProdukMakananActivity,
						"Gagal Terhubung Ke server", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
				Toast.makeText(this@ProdukMakananActivity, "Gagal Terhubung ke Server", Toast.LENGTH_LONG).show()
			}

		})
	}

	fun cari(){
		binding.svFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				return false
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				getApiKategoriMakanan("1",newText.toString())
				return true
			}
		})
	}
}
