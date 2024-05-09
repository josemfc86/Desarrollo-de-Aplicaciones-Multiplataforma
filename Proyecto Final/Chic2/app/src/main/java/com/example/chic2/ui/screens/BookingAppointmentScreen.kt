package com.example.chic2.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chic2.ChicScreen
import com.example.chic2.R
import com.example.chic2.network.Appointment
import com.example.chic2.network.Service
import com.example.chic2.ui.components.BookingButton
import com.example.chic2.ui.components.ButtonsGroup
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingAppointmentScreen(
    onCancelButtonClicked: () -> Unit,
    navController: NavController,
    selectedServicesIds: String?,
    chicViewModel: ChicViewModel,
    retryAction: () -> Unit
) {
    val state = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            // Bloquea los Domingos y días anteriores al actual para que no sean seleccionados.
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val dayOfWeek = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                        .toLocalDate().dayOfWeek
                    val isBeforeCurrentDate =
                        Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                            .toLocalDate().isBefore(LocalDate.now())
                    dayOfWeek != DayOfWeek.SUNDAY && !isBeforeCurrentDate
                } else {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.timeInMillis = utcTimeMillis
                    calendar[Calendar.DAY_OF_WEEK] != Calendar.SUNDAY &&
                            calendar[Calendar.DAY_OF_WEEK] != Calendar.SATURDAY
                }
            }

            // Permite seleccionar fechas a partir del año 2024.
            override fun isSelectableYear(year: Int): Boolean {
                return year >= 2024
            }
        }
    )

    val date = state.selectedDateMillis
    // Variable para manejar el estado de la hora
    var timeSelected by remember { mutableStateOf(R.string.app_name) }
    // Variable para manejar el estado de la hora formateada
    var formattedTime by remember { mutableStateOf<String>("") }
    // Variable para manejar el estado de la fecha
    var stringSelectedDate by remember { mutableStateOf<String>("") }
    // Variable para manejar el estado de la fecha formateada
    var formattedDate by remember { mutableStateOf<String>("") }
    // Variable para manejar el estado de la fecha y hora formateada
    var formattedDateTime by remember { mutableStateOf<String>("") }
    /* Variable para manejar el estado de la cita
    var appointment by remember { mutableStateOf<Appointment?>(null) }*/
    // Define el estado para controlar la visibilidad del cuadro de diálogo
    val showAppointmentDialog = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(16.dp)

    ) {
        Text(
            text = "Seleccione una fecha y hora para la cita:",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        DatePicker(
            state = state,
            title = null,
            headline = null,
            showModeToggle = false
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

        SelecTimeListRow(
            { newTime ->
                timeSelected = newTime
            }
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))


        /* variable para construir un String que permita modificar la parte de la fecha que se ha
         escogido para que aparezca en negrita*/
        val annotatedString = buildAnnotatedString {
            // Texto normal
            append("Cita seleccionada: ")

            // Parte en negrita
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringSelectedDate)
            }

        }
        Text(
            text = annotatedString,
            style = MaterialTheme.typography.bodyMedium
        )
        //TODO: Corregir que no aparezcan horas anteriores a la hora actual al momento de seleccionar la hora
        date?.let {
            val selectedDate =
                Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

            //Condicional para que muestre la cita seleccionada luego de escoger fecha y hora
            if (timeSelected != R.string.app_name) {

                stringSelectedDate =
                    "${selectedDate.dayOfMonth}/${selectedDate.monthValue}/${selectedDate.year} -" +
                            " ${stringResource(id = timeSelected)}"
            }

            if (selectedDate != null && timeSelected != R.string.app_name) {
                // Formateamos la fecha y la hora utilizando un formateador
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                formattedDate = selectedDate.format(dateFormatter)
                formattedTime =
                    LocalTime.parse(stringResource(id = timeSelected) + ":00")
                        .format(timeFormatter)

                formattedDateTime = "${formattedDate}T${formattedTime}"

                //Guarda la fecha y hora en "AppointmentUiState" para cuando se vaya a reservar la cita
                chicViewModel.saveDateTime(formattedDateTime)
            }

        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))

        Text(
            text = stringResource(id = R.string.selected_services),
            style = MaterialTheme.typography.titleMedium
        )

        //TODO: Verificar que cuando hayan varios servicios seleccionados se muestren todos en el scroll

        /*Este "if" muestra los servicios seleccionados teniendo en cuenta si se han pasado desde la pantalla
        "ProviderDetailScreen" o por el contrario los servicios son tomados del appointment guardado en el ViewModel
        antes de que el usuario iniciara sesión.*/
        if (selectedServicesIds != null) {
            // Observa los servicios seleccionados guardados en el ViewModel
            val selectedServicesState = chicViewModel.selectedServicesListUiState

            // Llama al método para obtener los servicios seleccionados cuando los IDs cambien
            LaunchedEffect(selectedServicesIds) {
                val idsList = selectedServicesIds.split(",").map { it.toLong() }
                chicViewModel.getSelectedServices(idsList)
            }

            // Muestra los servicios seleccionados
            SelectedServicesList(
                selectedServicesList = selectedServicesState,
                modifier = Modifier.weight(1f) // Establece una altura finita
            )
        } else {
            // Llama al método para obtener los servicios seleccionados cuando los IDs cambien
            LaunchedEffect(chicViewModel.uiState.value.selectedServicesIds) {
                val idsList =
                    chicViewModel.uiState.value.selectedServicesIds!!.split(",")
                        .map { it.toLong() }
                chicViewModel.getSelectedServices(idsList)
            }
            SelectedServicesList(
                selectedServicesList = chicViewModel.selectedServicesListUiState,
                modifier = Modifier.weight(1f) // Establece una altura finita
            )
        }


        //TODO: Crear un composable para mostrar el precio total de los servicios seleccionados

        Text(
            text = "Total = ${chicViewModel.uiState.value.totalPrice}€",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )



        //TODO: Si se ha iniciado sesión se deben recuperar los datos del usuario para crear la variable user
        /*val user: User? = User(
            "usuario2",
            "789012",
            "usuario2@email.com",
            "69876543",
            "manuel",
            Direction(
                null,
                "calle2",
                "barakaldo",
                stringResource(id = R.string.Valencia),
                "48920",
                stringResource(
                    id = R.string.Spain
                )
            )
        )*/

        //TODO: Recuperar los datos del proveedor para poder crear la variable provider
        /* val provider: Provider = Provider(
             "proveedor2",
             "478213P",
             "proveedor2@email.com",
             "9234615",
             "Cesar's peluqueria",
             Direction(
                 null,
                 "divino niño",
                 "Puerto La Cruz",
                 stringResource(id = R.string.Valencia),
                 "48910",
                 stringResource(
                     id = R.string.Spain
                 )
             ),
             Image(null, "http://10.0.2.2:8085/images/peluqueria2.jpg"), null
         )*/

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))

        ButtonsGroup(
            onCancelButtonClicked = onCancelButtonClicked,
            button = {
                BookingButton(
                    formattedDateTime = formattedDateTime,
                    onBookButtonClicked = {
                        //TODO: Modificar el "totalPrice" con el real.
                        chicViewModel.saveAppointmentViewModel(
                            Appointment(
                                id = null,
                                date = formattedDateTime,
                                totalPrice = 0,
                                user = chicViewModel.uiState.value.user,
                                provider = chicViewModel.uiState.value.provider,
                                services = chicViewModel.selectedServicesListUiState
                            )
                        )
                        if (chicViewModel.uiState.value.user == null) {
                            navController.navigate(ChicScreen.Loging.name)
                        } else {
                            val appointment = chicViewModel.uiState.value.appointment
                            chicViewModel.createAppointment(appointment = appointment!!)
                            showAppointmentDialog.value = true

                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                )
                // Observa el estado y muestra el diálogo cuando sea necesario
                if (showAppointmentDialog.value) {
                    AppointmentReservedDialog(
                        chicViewModel = chicViewModel,
                        navController = navController,
                        onDialogClose = {
                            showAppointmentDialog.value = false
                        } // Cierra el diálogo
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}


@Composable
fun SelecTimeListRow(
    onTimeSelected: (Int) -> Unit, // Función de callback para comunicar el tiempo seleccionado
    modifier: Modifier = Modifier
) {
    // Estado para rastrear el índice del elemento seleccionado
    var selectedItemIndex by remember { mutableStateOf(-1) }


    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier

    ) {
        items(SelecTimeData.indices.toList()) { index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(80.dp)
                    // Cambia el color si el elemento está seleccionado
                    .background(if (selectedItemIndex == index) Color.Green else Color.LightGray)
                    .clickable
                    {
                        // Actualiza el índice del elemento seleccionado
                        selectedItemIndex = index

                        // Llama a la función de callback para comunicar el tiempo seleccionado
                        onTimeSelected(SelecTimeData[index])

                    }

            ) {
                Text(
                    text = stringResource(id = SelecTimeData[index]),
                    // textAlign =
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )

            }

        }
    }
}

@Composable
fun SelectedServicesList(selectedServicesList: List<Service>, modifier: Modifier) {

    val selectedServicesLazyListState = rememberLazyListState()

    LazyColumn(modifier = modifier, state = selectedServicesLazyListState) {

        items(items = selectedServicesList) { service ->
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = service.nameService,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${service.price.toString()}€",
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
                    thickness = dimensionResource(R.dimen.thickness_divider)
                )
            }
        }

    }
}


@Composable
fun AppointmentReservedDialog(
    chicViewModel: ChicViewModel,
    navController: NavController,
    onDialogClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Reserva Exitosa") },
        text = { Text(text = "La cita se ha reservado con éxito.") },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Primero cierra el diálogo
                        onDialogClose()
                        // Luego realiza la navegación
                        navController.navigate(ChicScreen.Home.name)
                        //Reinicia el AppointmentUiState
                        chicViewModel.resetAppointmentUiState()
                    },
                    /*colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )*/
                ) {
                    Text("Aceptar")
                }
            }
        }
    )
}

private val SelecTimeData = listOf(
    R.string.time_09_00,
    R.string.time_09_30,
    R.string.time_10_00,
    R.string.time_10_30,
    R.string.time_11_00,
    R.string.time_11_30,
    R.string.time_12_00,
    R.string.time_12_30,
    R.string.time_13_00,
    R.string.time_13_30,
    R.string.time_16_00,
    R.string.time_16_30,
    R.string.time_17_00,
    R.string.time_17_30,
    R.string.time_18_00,
    R.string.time_18_30,
    R.string.time_19_00,
    R.string.time_19_30
)

/*
@Composable
fun UserAppointments(
    user: User,
    // onCancelButtonClicked: () -> Unit,
    //  navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = user.nameUser,
            modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = R.string.appointments),
            modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        var appointments by remember { mutableStateOf(user.appointments) }
        val appointmentsLazyListState = rememberLazyListState()


        LazyColumn(modifier = modifier, state = appointmentsLazyListState) {

            items(items = appointments, key = { it.id }) { appointment ->

                AppointmentListItem(appointment = appointment)

            }
        }
    }
}
*/

/*
@Composable
fun AppointmentListItem(
    appointment: Appointment,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = appointment.provider?.name ?: "Nombre no disponible",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = appointment.appointment,
            style = MaterialTheme.typography.headlineSmall
        )

        //  SelectedServicesList(selectedServicesList = appointment.services)

        Text(
            text = appointment.totalPrice.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
            thickness = dimensionResource(R.dimen.thickness_divider)
        )
    }

}*/
