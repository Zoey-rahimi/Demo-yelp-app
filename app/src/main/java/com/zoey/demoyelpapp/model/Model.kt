package com.zoey.demoyelpapp.model

import com.google.gson.annotations.SerializedName

data class Businesses(
    @SerializedName("businesses")
    val businesses: List<Business>?,

    @SerializedName("total")
    val total: Int?
)

data class Business(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("review_count")
    val reviewCount: Int?,

    @SerializedName("location")
    val location: Location?
)

data class Location(
    @SerializedName("display_address")
    val displayAddress: List<String>?
)