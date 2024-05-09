package com.example.chic2

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chic2.ui.screens.ChicViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chic2.network.Address
import com.example.chic2.network.Appointment
import com.example.chic2.network.Image
import com.example.chic2.network.Provider
import com.example.chic2.network.Service
import com.example.chic2.network.User
import com.example.chic2.ui.screens.ProvidersScreen
import com.example.chic2.ui.screens.HomeScreen
import com.example.chic2.ui.screens.LogingScreen
import com.example.chic2.ui.screens.ProviderDetailsScreen
import com.example.chic2.ui.screens.BookingAppointmentScreen
import com.example.chic2.ui.screens.EditUserScreen
import com.example.chic2.ui.screens.SigningScreen
import com.example.chic2.ui.screens.UserAppointmentsScreen

/**
 * enum values that represent the screens in the app
 */
enum class ChicScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Providers(title = R.string.hair_salons),
    Loging(title = R.string.loging),
    BookingAppointment(title = R.string.booking_appointment_screen),
    Signing(title = R.string.signing),
    EditUser(title = R.string.edit_user_screen),
    AppointmentsUser(title = R.string.appointments_user_screen)
}


@Composable
fun ChicApp(
    chicViewModel: ChicViewModel = viewModel(factory = ChicViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    //TODO: Implementar esto, que es para que el usuario pueda regresar o navegar a las pantallas anteriores

    /*    val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = ChicScreen.valueOf(
            backStackEntry?.destination?.route ?: ChicScreen.Home.name
        )*/

    //val uiState by viewModel.uiState.collectAsState()

    //TODO: Crear la variable user que se recuperara del inicio de sesion y se enviará como parámetro a la pantalla de SelectAppointmentScreen

    NavHost(
        navController = navController,
        startDestination = ChicScreen.Home.name,

        ) {
        composable(route = ChicScreen.Home.name) {
            HomeScreen(
                navController = navController,
                modifier = Modifier.fillMaxHeight()
            )
        }

        composable(
            route = "providers/businessTypes/{businessType}",
            arguments = listOf(navArgument("businessType") { type = NavType.StringType })
        ) { backStackEntry ->
            ProvidersScreen(
                businessType = backStackEntry.arguments?.getString("businessType") ?: "",
                // onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
                // onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                chicViewModel = chicViewModel,
                navController = navController,
                retryAction = {},
                modifier = Modifier.fillMaxHeight()
            )
        }

        composable(
            route = "providerDetails/{providerUser}",
            arguments = listOf(navArgument("providerUser") { type = NavType.StringType })
        ) { backStackEntry ->
            ProviderDetailsScreen(
                providerUser = backStackEntry.arguments?.getString("providerUser") ?: "",
                //subtotal = uiState.price,
                //onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) },
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                chicViewModel = chicViewModel,
                navController = navController,
                retryAction = {
                    chicViewModel.getProviderDetails(
                        backStackEntry.arguments?.getString(
                            "providerUser"
                        ) ?: ""
                    )
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            )
        }

        composable(
            route = "appointment/{selectedServices}",
            arguments = listOf(navArgument("selectedServices") { type = NavType.StringType })
        ) { backStackEntry ->
            BookingAppointmentScreen(
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                navController = navController,
                selectedServicesIds = backStackEntry.arguments?.getString("selectedServices") ?: "",
                chicViewModel = chicViewModel,
                retryAction = {}
            )

        }

        composable(route = ChicScreen.Loging.name) {
            LogingScreen(
                chicViewModel = chicViewModel,
                navController = navController,
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                }
            )
        }

        /*Esta ruta lleva a la pantalla de BookingAppointmentScreen de nuevo luego de que el usuario
        se regista, recuperando los datos de la cita para poder hacer la reserva*/
        composable(route = ChicScreen.BookingAppointment.name) {
            BookingAppointmentScreen(
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                navController = navController,
                selectedServicesIds = null,
                chicViewModel = chicViewModel,
                retryAction = {}
            )
        }

        composable(route = ChicScreen.Loging.name) {
            LogingScreen(
                chicViewModel = chicViewModel,
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                navController = navController
            )
        }

        composable(route = ChicScreen.Signing.name) {
            SigningScreen(
                chicViewModel = chicViewModel,
                navController = navController,
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                }
            )
        }


        composable(route = ChicScreen.EditUser.name) {
            EditUserScreen(
                user = chicViewModel.uiState.value.user

             /*   chicViewModel = chicViewModel,
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                navController = navController*/
            )
        }

        composable(route = ChicScreen.AppointmentsUser.name) {
            UserAppointmentsScreen(
                appointments = chicViewModel.getUserAppointments(chicViewModel.uiState.value.user?.user),
                navController
                /*chicViewModel = chicViewModel,
                onCancelButtonClicked = {
                    chicViewModel.resetAppointmentUiState()
                    navController.popBackStack(ChicScreen.Home.name, inclusive = false)
                },
                navController = navController*/
            )
        }
    }



}