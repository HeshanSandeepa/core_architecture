package com.example.marsphotos.data.domain

import com.google.gson.annotations.SerializedName


data class MarsPhoto(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "img_src")
    val imgSrc: String
)
