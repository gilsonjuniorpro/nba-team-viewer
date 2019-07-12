package com.nba.ca.core

import com.nba.ca.util.Dominios
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    fun init() {
        Retrofit.Builder()
            .baseUrl(Dominios.URL_JSON_NBA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}