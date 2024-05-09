package com.example.chic2.model

import com.example.chic2.network.Appointment
import com.example.chic2.network.Provider
import com.example.chic2.network.User

//TODO: sacar el User de aqui porque cuando se le da al boton cancelar en cualquier pantalla el user se reinicia a null y por lo tanto perdería la sesión.
data class AppointmentUiState(
    val appointment: Appointment? = null,
    val user: User? = null,
    val provider: Provider? = null,
    val selectedServicesIds: String? = null,
    val formattedDateTime: String? = null,
    val totalPrice: Float = 0f
)
