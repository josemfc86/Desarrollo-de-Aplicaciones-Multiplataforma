package com.example.chic2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.chic2.R
import com.example.chic2.network.Provider
import com.example.chic2.network.Service
import com.example.chic2.ui.components.ButtonsGroup
import com.example.chic2.ui.components.NextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderDetailsScreen(
    providerUser: String,
    chicViewModel: ChicViewModel,
    onCancelButtonClicked: () -> Unit,
    navController: NavController,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        chicViewModel.getProviderDetails(providerUser = providerUser)
        val providerDetailsUiState = chicViewModel.providerDetailsUiState

        when (providerDetailsUiState) {
            is ProviderDetailsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is ProviderDetailsUiState.Success -> ProviderDetails(
                chicViewModel = chicViewModel,
                provider = providerDetailsUiState.provider,
                onCancelButtonClicked = onCancelButtonClicked,
                navController = navController,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
            )

            is ProviderDetailsUiState.Error -> ErrorScreen(
                retryAction,
                modifier = modifier.fillMaxSize()
            )

        }
    }

}


@Composable
fun ProviderDetails(
    chicViewModel: ChicViewModel,
    provider: Provider,
    onCancelButtonClicked: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(provider.imgSrc.path)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.hairdresser_img),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Text(
            text = provider.name,
            modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = provider.address.toString(),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .align(Alignment.Start)
        )

        Text(
            text = stringResource(id = R.string.services),
            modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        var services by remember { mutableStateOf(provider.services?: emptyList()) }
        val servicesLazyListState = rememberLazyListState()
        val selectedServicesIds = remember { mutableSetOf<Long>() }
        var totalPrice by remember{ mutableStateOf(0f) }


        LazyColumn(modifier = modifier, state = servicesLazyListState) {

            items(items = services, key = { it.id }) { service ->

                ServiceListItem(
                    service = service,
                    selectedServicesIds = selectedServicesIds,
                    onSelectionChange = { selected ->
                        if (selected) {
                            selectedServicesIds.add(service.id)
                            totalPrice += service.price
                        }
                    }
                )
            }

            item { // Grupo de botones para cancelar la seleccion o navegar a la siguiente pantalla.
                ButtonsGroup(
                    onCancelButtonClicked = onCancelButtonClicked,
                    button = {
                        NextButton(
                            onNextButtonClicked = { selectedServicesIds ->

                                val selectedIds =
                                    selectedServicesIds.joinToString(separator = ",") { it.toString() }

                                /* Guarda los servicios seleccionados en "AppointmentUiState" para cuando se vaya
                                 a reservar la cita */
                                chicViewModel.saveSelectedServicesViewModel(selectedIds)

                                //Guarda el precio total de la cita en "AppointmentUiState"
                                chicViewModel.saveTotalPrice(totalPrice)

                                // Navega a la siguiente pantalla con los servicios seleccionados.
                                navController.navigate("appointment/$selectedIds")

                            },
                            selectedServicesIds = selectedServicesIds,
                            modifier = Modifier
                                .weight(1f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }

        }
    }

}


@Composable
fun ServiceListItem(
    service: Service,
    selectedServicesIds: MutableSet<Long>,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isChecked = selectedServicesIds.contains(service.id)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked ->
                onSelectionChange(isChecked)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green // Color verde cuando checked es true
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = service.nameService,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = service.price.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
                thickness = dimensionResource(R.dimen.thickness_divider)
            )
        }
    }
}
