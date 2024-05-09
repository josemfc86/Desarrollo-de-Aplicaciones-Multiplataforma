package com.example.chic2.network

import kotlinx.serialization.Serializable


@Serializable
data class Appointment(
    val id: Long?,
    val date: String,
    val totalPrice: Long,
    val user: User?,
    val provider: Provider?,
    val services: List<Service>
)



