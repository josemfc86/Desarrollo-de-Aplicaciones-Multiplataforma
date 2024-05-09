package com.example.chic2.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chic2.ChicScreen
import com.example.chic2.R
import com.example.chic2.network.Address
import com.example.chic2.network.User
import com.example.chic2.ui.components.ButtonsGroup
import com.example.chic2.ui.components.ShowAlertDialog
import com.example.chic2.ui.components.SigningButton

@Composable
fun SigningScreen(
    chicViewModel: ChicViewModel,
    navController: NavController,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var user by remember { mutableStateOf("") }
    var userEmpty by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordEmpty by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailEmpty by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf("") }
    var phoneEmpty by remember { mutableStateOf(false) }

    var nameUser by remember { mutableStateOf("") }
    var nameUserEmpty by remember { mutableStateOf(false) }

    var street by remember { mutableStateOf("") }

    var city by remember { mutableStateOf("") }

    var selectedProvince by remember { mutableStateOf<Province?>(null) }

    var zipCode by remember { mutableStateOf("") }
    var zipCodeEmpty by remember { mutableStateOf(false) }

    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var selectedCountryEmpty by remember { mutableStateOf(false) }

    val userExists by chicViewModel.userExists.collectAsState()
    var showAlertDialog by remember { mutableStateOf<Boolean>(false) }
    var alertDialogMessage by remember { mutableStateOf("") }


    // Variables para controlar la visibilidad de los dropdowns
    var provinceMenuExpanded by remember { mutableStateOf(false) }
    var countryMenuExpanded by remember { mutableStateOf(false) }

    // Define la función para verificar si algún campo está vacío
    fun validateFields(): Boolean {
        // Actualiza los estados de error basado en si los campos están vacíos o no
        userEmpty = user.isBlank()
        passwordEmpty = password.isBlank()
        emailEmpty = email.isBlank()
        phoneEmpty = phone.isBlank()
        nameUserEmpty = nameUser.isBlank()
        zipCodeEmpty = zipCode.isBlank()
        selectedCountryEmpty = selectedCountry == null
        // Retorna si algún campo está vacío
        return !(userEmpty || passwordEmpty || emailEmpty || phoneEmpty || nameUserEmpty || zipCodeEmpty || selectedCountryEmpty)
    }

    // Colores para los campos cuando están vacíos
    val errorTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Red,
        errorBorderColor = Color.Red
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.title_loging_screen),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))
        OutlinedTextField(
            value = user,
            onValueChange = {
                user = it.trim()
            },
            label = { Text(stringResource(id = R.string.user)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = userEmpty, // Si el campo está vacío, muestra el borde en rojo
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it.trim()
            },
            label = { Text(stringResource(id = R.string.password)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = passwordEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it.trim()
            },
            label = { Text(stringResource(id = R.string.email)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = emailEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it.trim()
            },
            label = { Text(stringResource(id = R.string.phone)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = phoneEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = nameUser,
            onValueChange = {
                nameUser = it.trim()
            },
            label = { Text(stringResource(id = R.string.name_user)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = nameUserEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = street,
            onValueChange = { street = it.trim() },
            label = { Text(stringResource(id = R.string.street)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text(stringResource(id = R.string.city)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        // Menu desplegable para seleccionar la provincia
        OutlinedTextField(
            value = selectedProvince?.let { stringResource(id = it.province) } ?: "",
            onValueChange = {},
            label = { Text(stringResource(id = R.string.province)) },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    "Dropdown",
                    Modifier.clickable { provinceMenuExpanded = true }
                )
            },
            readOnly = true, // Hace que el campo de texto sea de solo lectura
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = provinceMenuExpanded,
            onDismissRequest = { provinceMenuExpanded = false }
        ) {
            Province.values().forEach { province ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = province.province)) },
                    onClick = {
                        selectedProvince = province
                        provinceMenuExpanded = false
                    })
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        OutlinedTextField(
            value = zipCode,
            onValueChange = {
                zipCode = it.trim()
            },
            label = { Text(stringResource(id = R.string.zip_code)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            isError = zipCodeEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_OutlinedTextField))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
        // Menu desplegable para seleccionar el país
        OutlinedTextField(
            value = selectedCountry?.let { stringResource(id = it.country) } ?: "",
            onValueChange = {},
            label = { Text(stringResource(id = R.string.country)) },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    "Dropdown",
                    Modifier.clickable { countryMenuExpanded = true }
                )
            },
            isError = selectedCountryEmpty,
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = countryMenuExpanded,
            onDismissRequest = { countryMenuExpanded = false }
        ) {
            Country.values().forEach { country ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = country.country)) },
                    onClick = {
                        selectedCountry = country
                        countryMenuExpanded = false
                    })
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))

        ButtonsGroup(
            onCancelButtonClicked = onCancelButtonClicked,
            button = {
                SigningButton(
                    onSigningButtonClicked = {
                        // Verifica si algún campo obligatorio está vacío
                        if (validateFields()) {
                            //Comprobamos si existe el usuario
                            chicViewModel.checkUserExists(user)
                            if (userExists != null) {
                                if (userExists == true) {
                                    // Muestra un diálogo de alerta diciendo que el usuario existe
                                    alertDialogMessage = "¡El usuario ya existe!, intenta con otro."
                                    showAlertDialog = true
                                } else {

                                    val newUser = User(
                                        user = user,
                                        password = password,
                                        email = email,
                                        phone = phone,
                                        nameUser = nameUser,
                                        Address(
                                            street = street,
                                            city = city,
                                            province = selectedProvince?.name ?: "",
                                            zipCode = zipCode,
                                            country = selectedCountry?.name ?: ""
                                        )
                                    )
                                    //Guarda el usuario en "AppointmentUiState" para cuando se vaya a reservar la cita
                                    chicViewModel.saveUserViewModel(newUser)
                                    //Crea el usuario en la base de datos del servidor
                                    chicViewModel.createUser(newUser)
                                    if(chicViewModel.createUiState == CreateUiState.Success){
                                        alertDialogMessage = "Usuario creado con éxito"
                                        showAlertDialog = true
                                    }else{
                                        alertDialogMessage = "Fallo al crear el usuario"
                                        showAlertDialog = true
                                    }

                                    if (chicViewModel.uiState.value.appointment == null) {
                                        navController.navigate(ChicScreen.Home.name)
                                    } else {
                                        navController.navigate(ChicScreen.BookingAppointment.name)
                                    }
                                }
                            }

                        } else {
                            return@SigningButton
                        }
                    }
                )
            },
            modifier = Modifier
        )


        // Muestra un diálogo de alerta diciendo que el usuario existe.
        if (showAlertDialog) {
            ShowAlertDialog(
                onDismissRequest = {},
                title = "",
                message = alertDialogMessage,
                onConfirmClicked = {
                    showAlertDialog = false
                }
            )
        }
    }
}


enum class Province(@StringRes val province: Int) {
    Albacete(province = R.string.Albacete),
    Alicante(province = R.string.Alicante),
    Almería(province = R.string.Almería),
    Alava(province = R.string.Alava),
    Asturias(province = R.string.Asturias),
    Avila(province = R.string.Avila),
    Badajoz(province = R.string.Badajoz),
    Baleares(province = R.string.Baleares),
    Barcelona(province = R.string.Barcelona),
    Bizkaia(province = R.string.Bizkaia),
    Burgos(province = R.string.Burgos),
    Caceres(province = R.string.Caceres),
    Cadiz(province = R.string.Cadiz),
    Cantabria(province = R.string.Cantabria),
    Castellon(province = R.string.Castellon),
    Ciudad_Real(province = R.string.Ciudad_Real),
    Cordoba(province = R.string.Cordoba),
    A_Coruña(province = R.string.A_Coruña),
    Cuenca(province = R.string.Cuenca),
    Gipuzkoa(province = R.string.Gipuzkoa),
    Girona(province = R.string.Girona),
    Granada(province = R.string.Granada),
    Guadalajara(province = R.string.Guadalajara),
    Huelva(province = R.string.Huelva),
    Huesca(province = R.string.Huesca),
    Jaen(province = R.string.Jaen),
    Leon(province = R.string.Leon),
    Lleida(province = R.string.Lleida),
    Lugo(province = R.string.Lugo),
    Madrid(province = R.string.Madrid),
    Malaga(province = R.string.Malaga),
    Murcia(province = R.string.Murcia),
    Navarra(province = R.string.Navarra),
    Ourense(province = R.string.Ourense),
    Palencia(province = R.string.Palencia),
    Las_Palmas(province = R.string.Las_Palmas),
    Pontevedra(province = R.string.Pontevedra),
    La_Rioja(province = R.string.La_Rioja),
    Salamanca(province = R.string.Salamanca),
    Santa_Cruz_de_Tenerife(province = R.string.Santa_Cruz_de_Tenerife),
    Segovia(province = R.string.Segovia),
    Sevilla(province = R.string.Sevilla),
    Soria(province = R.string.Soria),
    Tarragona(province = R.string.Tarragona),
    Teruel(province = R.string.Teruel),
    Toledo(province = R.string.Toledo),
    Valencia(province = R.string.Valencia),
    Valladolid(province = R.string.Valladolid),
    Zamora(province = R.string.Zamora),
    Zaragoza(province = R.string.Zaragoza),
    Ceuta(province = R.string.Ceuta),
    Melilla(province = R.string.Melilla)
}

enum class Country(@StringRes val country: Int) {
    España(country = R.string.Spain)
}