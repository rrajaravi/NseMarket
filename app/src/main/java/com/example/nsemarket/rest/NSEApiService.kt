package com.example.nsemarket.rest

import com.example.nsemarket.models.NseResponse
import retrofit2.Call
import retrofit2.http.GET

interface NSEApiService {

    @get:GET("equity-stockIndices?index=NIFTY%2050")
    val nifty50: Call<NseResponse?>?

    @get:GET("equity-stockIndices?index=NIFTY%20BANK")
    val niftyBank: Call<NseResponse?>?

    @get:GET("equity-stockIndices?index=NIFTY%20IT")
    val niftyIT: Call<NseResponse?>?
}