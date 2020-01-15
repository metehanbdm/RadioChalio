package com.metehanbadem.radiochalio.data

import com.metehanbadem.radiochalio.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RadioServiceProvider {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.SERVER_URL)
        .build()

    private val apiService = retrofit.create(RadioApiService::class.java)

    fun getRadioService(): RadioApiService = apiService
}