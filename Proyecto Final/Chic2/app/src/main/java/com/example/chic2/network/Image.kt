package com.example.chic2.network

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Long?,
    val path: String
)

