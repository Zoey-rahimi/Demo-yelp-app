package com.zoey.demoyelpapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class YelpApiService {
    private val baseUrl = "https://api.yelp.com/v3/"
    private val apiKey =
        "Bearer _ojNfk2-wOgICBqw28yrW4T1i_DOZwXPaAzSVZPNx7wLAJfeNamex4BKik5nuMhFnNO0wzudhUWC_i6m3UiDcqvpeu5rt8CvRp0ZU4fIdd6Khc7Ni_07v8R8dcWJXXYx"


    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(YelpApi::class.java)

    fun getBusinesses(): Single<Businesses> {
        return api.getBusinesses(apiKey)
    }
}