package com.example.chic2.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Clase de datos que define una proveedor e incluye un nombre, tipo, descripción y URL de la imagen.
@Serializable
data class Provider(
    val providerUser: String,
    val password: String?= null,
    val email: String,
    val phone: String,
    val name: String,
    val address: Address,
    @SerialName(value = "image")
    val imgSrc: Image,
    val businessType: List<String>,
    val services: List<Service>?
)





