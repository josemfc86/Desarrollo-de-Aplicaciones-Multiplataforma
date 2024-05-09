package com.example.chic2.network
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChicApiService {
    @GET("/api/providers/businessTypes/{businessType}")
    suspend fun getProvidersByBusinessType(@Path("businessType") businessType: String): List<Provider>

    @GET("/api/providers/{provider}")
    suspend fun getProviderDetails(@Path("provider") providerUser: String): Provider

    @GET("/api/services/{service}")
    suspend fun getService(@Path("service") id: Long): Service

//    @GET("/api/appointments")
//    suspend fun getAppointments(): List<Appointment>

    @GET("/api/appointments/users/{user}")
    suspend fun getUserAppointments(@Path("user") user: String?): List<Appointment>

    @POST("/api/appointments")
    suspend fun createAppointment(@Body appointment: Appointment): Response<Void>

    @POST("/api/users")
    suspend fun createUser(@Body user: User): Response<Void>

    @GET("/api/users/{user}")
    suspend fun getUser(@Path("user") user: String): User

    @GET("/api/users/check/{user}")
    suspend fun checkUserExists(@Path("user") user: String): Boolean
}