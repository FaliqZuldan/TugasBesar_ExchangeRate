package com.example.exchangerate.network

import com.example.exchangerate.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApiService {
    @GET("latest/{base}")
    fun getExchangeRates(@Path("base") baseCurrency: String): Call<ExchangeRateResponse>
}
