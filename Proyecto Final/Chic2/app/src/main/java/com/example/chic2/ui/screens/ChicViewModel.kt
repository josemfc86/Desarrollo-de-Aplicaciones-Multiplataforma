package com.example.chic2.ui.screens


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.chic2.Chic2Application
import com.example.chic2.data.ChicRepository
import com.example.chic2.model.AppointmentUiState
import com.example.chic2.network.Appointment
import com.example.chic2.network.Provider
import com.example.chic2.network.Service
import com.example.chic2.network.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

//Estado de la UI de las pantallas.
sealed interface ProvidersUiState {
    data class Success(val providers: List<Provider>) : ProvidersUiState
    object Error : ProvidersUiState
    object Loading : ProvidersUiState
}

sealed interface ProviderDetailsUiState {
    data class Success(val provider: Provider) : ProviderDetailsUiState
    object Error : ProviderDetailsUiState
    object Loading : ProviderDetailsUiState
}

sealed interface ServiceUiState {
    data class Success(val service: Service) : ServiceUiState
    object Error : ServiceUiState
    object Loading : ServiceUiState
}

sealed interface AppointmentsUiState {
    data class Success(val appointments: List<Appointment>) : AppointmentsUiState
    object Error : AppointmentsUiState
    object Loading : AppointmentsUiState
}

/*sealed interface UserUiState {
    data class Success(val user: User) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}*/

sealed interface CreateUiState {
    object Success : CreateUiState
    object Error : CreateUiState
    object Loading : CreateUiState
}


//ViewModel que contiene los datos de la aplicación y el método para recuperar los datos
class ChicViewModel(private val chicRepository: ChicRepository) : ViewModel() {
    var providerUiState: ProvidersUiState by mutableStateOf(ProvidersUiState.Loading)
        private set

    var providerDetailsUiState: ProviderDetailsUiState by mutableStateOf(ProviderDetailsUiState.Loading)
        private set

    var serviceUiState: ServiceUiState by mutableStateOf(ServiceUiState.Loading)
        private set

    var selectedServicesListUiState = mutableStateListOf<Service>()
        private set

    var appointmentsUiState: AppointmentsUiState by mutableStateOf(AppointmentsUiState.Loading)
        private set

    var userUiState =  mutableStateOf<User?>(null)
        private set

    var createUiState: CreateUiState by mutableStateOf(CreateUiState.Loading)
        private set

    //Controla el estado de los diferentes objetos que necesitamos para hacer la reserva de la cita
    private val _uiState = MutableStateFlow(AppointmentUiState())
    val uiState: StateFlow<AppointmentUiState> = _uiState.asStateFlow()

    private val _userExists = MutableStateFlow<Boolean?>(null)
    val userExists: StateFlow<Boolean?> = _userExists

    fun getProvidersByBusinessType(businessType: String) {
        viewModelScope.launch {
            try {
                providerUiState = ProvidersUiState.Success(
                    chicRepository.getProvidersByBusinessType(businessType)
                )
            } catch (e: IOException) {
                providerUiState = ProvidersUiState.Error
                Log.e("ProvidersScreen", "Error obteniendo los proveedores: ${e.message}")
            }
        }
    }

    fun getProviderDetails(providerUser: String) {
        viewModelScope.launch {
            try {
                providerDetailsUiState = ProviderDetailsUiState.Success(
                    chicRepository.getProviderDetails(providerUser)
                )
            } catch (e: IOException) {
                providerDetailsUiState = ProviderDetailsUiState.Error
                Log.e(
                    "ProviderDetailsScreen",
                    "Error obteniendo los detalles del proveedor: ${e.message}"
                )
            }
        }
    }

    /*  fun getService(id: Long) {
          viewModelScope.launch {
              try {
                  serviceUiState = ServiceUiState.Success(
                      chicRepository.getService(id)
                  )
              } catch (e: IOException) {
                  serviceUiState = ServiceUiState.Error
                  Log.e(
                      "SelecAppointmentScreen",
                      "Error obteniendo los servicios del proveedor: ${e.message}"
                  )
              }
          }
      }*/

    // Método para obtener los servicios seleccionados
    fun getSelectedServices(selectedServicesIds: List<Long>) {
        viewModelScope.launch {
            try {
                val services = mutableListOf<Service>()
                for (id in selectedServicesIds) {
                    val service = chicRepository.getService(id)
                    services.add(service)
                }
                // Limpia la lista actual y agrega los nuevos servicios
                selectedServicesListUiState.clear()
                selectedServicesListUiState.addAll(services)
            } catch (e: IOException) {
                serviceUiState = ServiceUiState.Error
                Log.e(
                    "SelecAppointmentScreen",
                    "Error obteniendo los servicios del proveedor: ${e.message}"
                )
            }
        }
    }

    /*  fun getAppointments() {
          viewModelScope.launch {
              try {
                  appointmentsUiState = AppointmentsUiState.Success(
                      chicRepository.getAppointments()
                  )
              } catch (e: IOException) {
                  appointmentsUiState = AppointmentsUiState.Error
                  Log.e(
                      "ProviderDetailsScreen",
                      "Error obteniendo los detalles del proveedor: ${e.message}"
                  )
              }
          }
      }*/

    fun getUserAppointments(user: String?) {
        viewModelScope.launch {
            try {
                appointmentsUiState = AppointmentsUiState.Success(
                    chicRepository.getUserAppointments(user)
                )
            } catch (e: IOException) {
                appointmentsUiState = AppointmentsUiState.Error
                Log.e(
                    "ProviderDetailsScreen",
                    "Error obteniendo los detalles del proveedor: ${e.message}"
                )
            }
        }
    }

    fun getUser(user: String) {
        viewModelScope.launch {
            try {
                val userResult = chicRepository.getUser(user)
                userUiState.value = userResult

            } catch (e: IOException) {
                Log.e("User", "Error obteniendo el usuario: ${e.message}")
            }
        }
    }

    //Método para chequear si un usuario existe
    fun checkUserExists(user: String) {
        viewModelScope.launch {
            try {
                _userExists.value = chicRepository.checkUserExists(user)

            } catch (e: Exception) {
                Log.e("checkUserExists", "Error verificando si el usuario existe: ${e.message}")
                _userExists.value = null
            }
        }
    }

    // Método para crear un usuario
    fun createUser(user: User) {
        viewModelScope.launch {
            createUiState = CreateUiState.Loading
            try {
                val response = chicRepository.createUser(user)
                if (response.isSuccessful) {
                    createUiState = CreateUiState.Success
                } else {
                    createUiState = CreateUiState.Error
                    Log.e("CreateUser", "Error creando el usuario en el servidor: ${response.message()}")
                }

            } catch (e: IOException) {
                createUiState = CreateUiState.Error
                Log.e("CreateUser", "Error creando el usuario: ${e.message}")
            }
        }
    }


    // Método para crear una cita
    fun createAppointment(appointment: Appointment) {
        viewModelScope.launch {
            createUiState = CreateUiState.Loading
            try {
                val response = chicRepository.createAppointment(appointment)
                if (response.isSuccessful) {
                    createUiState = CreateUiState.Success
                } else {
                    createUiState = CreateUiState.Error
                    Log.e("CreateAppointment", "Error creando la cita en el servidor: ${response.message()}")
                }

            } catch (e: IOException) {
                createUiState = CreateUiState.Error
                Log.e("CreateAppointment", "Error creando la cita: ${e.message}")
            }
        }
    }

    fun saveAppointmentViewModel(appointment: Appointment) {
        _uiState.update { currentState ->
            currentState.copy(appointment = appointment)
        }
    }

    fun saveUserViewModel(user: User) {
        _uiState.update { currentState ->
            currentState.copy(user = user)
        }
    }

    fun saveProviderViewModel(provider: Provider) {
        _uiState.update { currentState ->
            currentState.copy(provider = provider)
        }
    }

    fun saveSelectedServicesViewModel(selectedServices: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedServicesIds = selectedServices)
        }
    }

    fun saveDateTime(formattedDateTime: String) {
        _uiState.update { currentState ->
            currentState.copy(formattedDateTime = formattedDateTime)
        }
    }

    fun saveTotalPrice(totalPrice: Float) {
        _uiState.update { currentState ->
            currentState.copy(totalPrice = totalPrice)
        }
    }

    fun resetAppointmentUiState() {
        _uiState.value = AppointmentUiState()
    }

    //Fábrica para [ChicViewModel] que toma [ChicRepository] como dependencia
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Chic2Application)
                val chicRepository = application.container.chicRepository
                ChicViewModel(chicRepository = chicRepository)
            }
        }
    }
}