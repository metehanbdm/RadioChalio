package com.metehanbadem.radiochalio.data

import com.metehanbadem.radiochalio.data.Radio
import io.reactivex.Single
import retrofit2.http.GET

interface RadioApiService{

    @GET("popularRadios.json")
    fun getPopularRadios(): Single<List<Radio>>
    @GET("locationRadios.json")
    fun getLocationRadios(): Single<List<Radio>>


}