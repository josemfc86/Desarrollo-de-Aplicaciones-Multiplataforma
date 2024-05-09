package com.example.chic2.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user: String,
    val password: String?= null,
    val email: String,
    val phone: String,
    @SerialName(value = "name")
    val nameUser: String,
    val address: Address,
 //   val appointments: List<Appointment>
)
