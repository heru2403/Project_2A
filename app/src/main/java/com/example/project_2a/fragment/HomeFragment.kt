package com.example.project_2a.fragment

import android.net.DnsResolver
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.project_2a.R
import com.example.project_2a.adapter.ProdukXiaomiAdapter
import com.example.project_2a.config.Network
import com.example.project_2a.databinding.FragmentHomeBinding
import com.example.project_2a.model.ProdukModel
import com.example.project_2a.model.SliderModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {

	//private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
	private lateinit var binding: FragmentHomeBinding
	private lateinit var produkXiaomiAdapter: ProdukXiaomiAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		binding = FragmentHomeBinding.inflate(inflater,container,false)
		// Inflate the layout for this fragment
		return binding.root
	}

	override fun onResume() {
		super.onResume()
		getApiSlider()
		getApiProduk()

	}

	private fun getApiProduk(){
		val produk = Network.service().getProduk()
		produk.enqueue(object : retrofit2.Callback<ProdukModel>{
			override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
				if (response.isSuccessful){
					val hasil = response.body()
					if (hasil!!.success==1){
						//tampilkan data produk
						setRecXiaomi(hasil.list_xiaomi)
					}
				} else{
					Toast.makeText(activity, "Gagal terhubung ke API", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<ProdukModel>, t: Throwable) {

			}
		})
	}

	private fun setRecXiaomi(data: ArrayList<ProdukModel.DataXiaomi>){
		produkXiaomiAdapter = ProdukXiaomiAdapter(data)
		binding.rvXiaomi.adapter = produkXiaomiAdapter
	}

	//sebuah fungsi/method mengambil data dari API
	private fun getApiSlider() {
		val sliders = Network.service().getSlider()
		sliders.enqueue(object : retrofit2.Callback<SliderModel> {
			override fun onResponse(call: Call<SliderModel>, response: Response<SliderModel>) {
				if (response.isSuccessful) {
					val hasil = response.body()
					if (hasil!!.success == 1) {
						//tampilkan data sliders
						setSliders(hasil.data)
					}
				}
			}

			override fun onFailure(call: Call<SliderModel>, t: Throwable) {
			}
		})
	}

	private fun setSliders(sliders: List<SliderModel.Result>){
		val imageAdd = ArrayList<SlideModel>()
		for (slide in sliders){
			imageAdd.add(SlideModel(slide.image,slide.deskripsi))
		}
		binding.imageSlider.setImageList(imageAdd,ScaleTypes.FIT)
	}
}
