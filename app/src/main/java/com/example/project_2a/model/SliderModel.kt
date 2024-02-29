package com.example.project_2a.model

data class SliderModel(val success: Int, val message: String, val data: ArrayList<Result>){
    data class Result(val image: String, val deskripsi: String)
}
