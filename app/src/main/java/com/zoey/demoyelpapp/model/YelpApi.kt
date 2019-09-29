package com.zoey.demoyelpapp.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface YelpApi {

    @GET("businesses/search?term=coffee&location=sydney")
    fun getBusinesses(@Header("Authorization") key: String): Single<Businesses>
}