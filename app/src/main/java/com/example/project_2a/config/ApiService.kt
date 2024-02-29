package com.example.project_2a.config

import com.example.project_2a.model.ProdukModel
import com.example.project_2a.model.SliderModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api_2a/index.php?folder=dashboard&file=sliders") //diambil dari link insomnia folder slider
    fun getSlider(): Call<SliderModel>

	@GET("api_2a/index.php?folder=dashboard&file=produk")
	fun getProduk(): Call<ProdukModel>

	@GET("api_2a/index.php?folder=produk&file=produkCategori")
	fun getProdukKategori(@Query("id_category") id_category: String,
												@Query("filter") filter: String) :
												Call<ProdukModel>

	@GET("api_2a/index.php?folder=produk&file=produkMakananCategori")
	fun getProdukMakananKategori(@Query("id_makanan") id_makanan: String,
						  @Query("filter") filter: String) :
			Call<ProdukModel>
}
