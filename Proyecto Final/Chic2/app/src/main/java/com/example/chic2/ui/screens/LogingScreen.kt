package com.example.chic2.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chic2.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.chic2.ChicScreen
import com.example.chic2.ui.components.ButtonsGroup
import com.example.chic2.ui.components.ShowAlertDialog
import com.example.chic2.ui.components.logingButton

@Composable
fun LogingScreen(
    chicViewModel: ChicViewModel,
    onCancelButtonClicked: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val userExists by chicViewModel.userExists.collectAsState()
    var showAlertDialog by remember { mutableStateOf<Boolean>(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text(text = stringResource(id = R.string.user)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid)))

        ButtonsGroup(
            onCancelButtonClicked = onCancelButtonClicked,
            button = {
                logingButton(
                    user = user,
                    password = password,
                    onLogingButtonClicked = { user, password ->
                        //Comprobamos que exista el usuario
                        chicViewModel.checkUserExists(user)
                        if (userExists != null) {
                            if (userExists == true)
                            //TODO: debe crearse una tabla de usuario y contraseña en el servidor con su clase y servidor, es más seguro
                            //Recuperamos el usuario del servidor
                                chicViewModel.getUser(user)
                            val userUiState = chicViewModel.userUiState
                            if (user == userUiState.value!!.user && password == userUiState.value!!.password) {
                                navController.navigate("HomeScreen")
                            } else {
                                // Muestra un diálogo de alerta al final diciendo que el usuario no existe
                                showAlertDialog = true
                            }
                        } }
                )
            }
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_mid))) 

        Text(
            text = "¿No tienes cuenta? Regístrate",
            modifier = Modifier
                .clickable {
                    // Navegar a la pantalla de registro
                    navController.navigate(ChicScreen.Signing.name)
                }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        // Muestra un diálogo de alerta diciendo que el usuario existe
        if (showAlertDialog){
            ShowAlertDialog(
                onDismissRequest = {},
                title = "",
                message = "El usuario no existe",
                onConfirmClicked = {
                    showAlertDialog = false
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SigningScreenPreview() {
    val navController = rememberNavController()
    // Crea una instancia de tu ViewModel de prueba o usa un ViewModel real si es posible y seguro hacerlo.
    val chicViewModel: ChicViewModel = viewModel(factory = ChicViewModel.Factory)
    // Llama a tu Composable con los objetos de prueba
    LogingScreen(
        chicViewModel = chicViewModel,
        onCancelButtonClicked = {}, // Proporciona un comportamiento de no-op para el preview
        navController = navController,
        modifier = Modifier
    )

}
