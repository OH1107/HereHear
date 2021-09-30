package com.ssafy.herehear.model.network

import com.ssafy.herehear.model.network.api.RetrofitInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var api: RetrofitInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://j5b105.p.ssafy.io:8081/api/v1/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitInterface::class.java)
    }

}