package com.example.chic2.network

import kotlinx.serialization.Serializable

@Serializable
data class Address (
    val id: Long? = null,
    val street: String,
    val city: String,
    val province: String,
    val zipCode: String,
    val country: String
){
    override fun toString(): String {
        return "$street, $city, $province, $zipCode"
    }
}
