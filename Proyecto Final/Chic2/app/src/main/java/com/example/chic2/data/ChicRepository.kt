package com.example.chic2.data

import com.example.chic2.network.Appointment
import com.example.chic2.network.ChicApiService
import com.example.chic2.network.Provider
import com.example.chic2.network.Service
import com.example.chic2.network.User
import retrofit2.Response

//El repositorio recupera datos de la fuente de datos subyacente.
interface ChicRepository {
    suspend fun getProvidersByBusinessType(businessType: String): List<Provider>
    suspend fun getProviderDetails(providerUser: String): Provider
    suspend fun getService(id: Long): Service
    //    suspend fun getAppointments(): List<Appointment>
    suspend fun getUserAppointments(user: String?): List<Appointment>
    suspend fun getUser(user: String): User?
    suspend fun createUser(user: User): Response<Void>
    suspend fun checkUserExists(user: String): Boolean
    suspend fun createAppointment(appointment: Appointment): Response<Void>

}

//Red de Implementación de un repositorio que recupera datos de la fuente de datos subyacente.
class NetworkChicRepository(private val chicApiService: ChicApiService): ChicRepository{
    //Recupera la lista de proveedores de la fuente de datos subyacente
    override suspend fun getProvidersByBusinessType(businessType: String): List<Provider> {
        return chicApiService.getProvidersByBusinessType(businessType)
    }

    //Recupera los detalles de un proveedor de la fuente de datos subyacente
    override suspend fun getProviderDetails(providerUser: String): Provider {
        return chicApiService.getProviderDetails(providerUser)
    }

    //Recupera los detalles de un servicio de la fuente de datos subyacente
    override suspend fun getService(id: Long): Service {
        return chicApiService.getService(id)
    }

    /* //Recupera todas las citas de la fuente de datos subyacente
     override suspend fun getAppointments(): List<Appointment> {
         return chicApiService.getAppointments()
     }*/

    //Recupera todas las citas de un usuario de la fuente de datos subyacente
    override suspend fun getUserAppointments(user: String?): List<Appointment> {
        return chicApiService.getUserAppointments(user)
    }

    //Recupera un usuario de la fuente de datos subyacente
    override suspend fun getUser(user: String): User {
        return chicApiService.getUser(user)
    }

    override suspend fun createUser(user: User): Response<Void> {
        return chicApiService.createUser(user)
    }

    override suspend fun checkUserExists(user: String): Boolean {
        return chicApiService.checkUserExists(user)
    }

    override suspend fun createAppointment(appointment: Appointment): Response<Void> {
        return chicApiService.createAppointment(appointment)
    }

}