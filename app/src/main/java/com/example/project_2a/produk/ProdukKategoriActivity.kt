package com.example.project_2a.produk

import android.net.DnsResolver.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.example.project_2a.R
import com.example.project_2a.adapter.ProdukCategoriAdapter
import com.example.project_2a.config.Network
import com.example.project_2a.databinding.ActivityProdukKategoriBinding
import com.example.project_2a.model.ProdukModel
import retrofit2.Call
import retrofit2.Response

class ProdukKategoriActivity : AppCompatActivity() {

	private lateinit var binding: ActivityProdukKategoriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		binding = ActivityProdukKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

	override fun onResume(){
		super.onResume()
		cari()
		getApiKategoriProduk("1","")
	}

	fun getApiKategoriProduk(id_category: String, filter: String){
		val kategoriProduk = Network.service().getProdukKategori(id_category, filter)
		kategoriProduk.enqueue(object: retrofit2.Callback<ProdukModel>{
			override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
				if(response.isSuccessful){
					val hasil = response.body()
					if(hasil!!.success==1){
						binding.imgNotFound.visibility = View.GONE
						binding.rvProduk.visibility = View.VISIBLE
						//tampilkan isi dari web API ke Adapter
						val produkCategoriAdapter = ProdukCategoriAdapter(hasil.list_produk)
						binding.rvProduk.adapter = produkCategoriAdapter
					} else{
						binding.imgNotFound.visibility = View.VISIBLE
						binding.rvProduk.visibility = View.GONE
					}
				} else{
					Toast.makeText(this@ProdukKategoriActivity,
						"Gagal Terhubung Ke server", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
				Toast.makeText(this@ProdukKategoriActivity, "Gagal Terhubung ke Server", Toast.LENGTH_LONG).show()
			}

		})
	}

	fun cari(){
		binding.svFilter.setOnQueryTextListener(object : OnQueryTextListener{
			override fun onQueryTextSubmit(query: String?): Boolean {
				return false
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				getApiKategoriProduk("1",newText.toString())
				return true
			}
		})
	}
}
