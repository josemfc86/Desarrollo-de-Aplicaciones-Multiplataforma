package com.example.chic2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chic2.R
import com.example.chic2.network.Address
import com.example.chic2.network.Appointment
import com.example.chic2.network.Image
import com.example.chic2.network.Provider
import com.example.chic2.network.Service
import com.example.chic2.network.User

@Composable
fun UserAppointmentsScreen(appointments: List<Appointment>, navController: NavController) {

    Scaffold(

        bottomBar = {
            TheBottomNavigation(navController)

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                //TODO: Asignar un recurso string al texto.
                text = stringResource(id = R.string.appointments_user_screen),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(appointments) { appointment ->
                    AppointmentItem(appointment)
                }
            }

        }
    }

}

@Composable
fun AppointmentItem(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${appointment.provider?.name ?: "Unknown"}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${appointment.date}",  // Asegúrate de que el formato de la fecha es el adecuado
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Total: ${appointment.totalPrice}€",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/*@Preview
@Composable
fun PreviewAppointmentsScreen() {
    val appointments = listOf(
        Appointment(
            id = 1,
            date = "Cesar's peluquería",
            totalPrice = 12,
            user = User(
                "jm",
                null,
                "asidhalksd",
                "54456",
                "odpaospdja",
                address = Address(
                    null,
                    "skuhfgskhf",
                    "siehf",
                    "Province.Bizkaia",
                    "68453",
                    "Country.España"
                )
            ),
            provider = Provider(
                "juofjdw",
                null,
                "sojf",
                "spfksf",
                "Cesar's peluquería",
                address = Address(
                    null,
                    "skuhfgskhf",
                    "siehf",
                    "Province.Bizkaia",
                    "68453",
                    "Country.España"
                ),
                Image(id = 1, path = "sfd"),
                listOf("Hair_Salon", "Barbershop"),
                listOf(
                    Service(
                        id = 1,
                        nameService = "Haircut",
                        price = 25.0f,
                        genre = "male"
                    )
                )
            ),
            services = listOf(
                Service(
                    id = 1,
                    nameService = "Haircut",
                    price = 25.0f,
                    genre = "male"
                ),
                Service(
                    id = 1,
                    nameService = "Haircut",
                    price = 25.0f,
                    genre = "male"
                )
            )
        )
    )
    UserAppointmentsScreen(appointments = appointments)
}*/
/*chicViewModel.getUserAppointments("usuario1")
val appointmentsUiState = chicViewModel.appointmentsUiState
val appointmentsLazyListState = rememberLazyListState()
when (appointmentsUiState) {
    is AppointmentsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())

    is AppointmentsUiState.Success ->
    LazyColumn(modifier = Modifier, state = appointmentsLazyListState) {
        items(items = appointmentsUiState.appointments, key = { it.id }) { appointment ->
            AppointmentListItem(appointment = appointment)
        }
    }

    is AppointmentsUiState.Error -> ErrorScreen(
    retryAction,
    modifier = Modifier.fillMaxSize()
    )
}*/
