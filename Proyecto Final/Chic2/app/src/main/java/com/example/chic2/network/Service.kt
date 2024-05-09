package com.example.chic2.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Clase de datos que define un servicio con sus propiedades.
@Serializable
data class Service(
    val id: Long,
    @SerialName(value = "name")
    val nameService: String,
    val price: Float,
    val genre: String
)
