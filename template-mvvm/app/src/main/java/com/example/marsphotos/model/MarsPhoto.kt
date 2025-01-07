package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class MarsPhoto(
    @SerialName(value = "id")
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
