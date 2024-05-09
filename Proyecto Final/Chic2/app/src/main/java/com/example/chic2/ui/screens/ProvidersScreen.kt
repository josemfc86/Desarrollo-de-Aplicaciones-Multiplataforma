package com.example.chic2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.chic2.R
import com.example.chic2.network.Provider
import com.example.chic2.ui.components.ProfileImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvidersScreen(
    businessType: String,
    chicViewModel: ChicViewModel,
    navController: NavController,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProviderSearchBar(chicViewModel)

        },
        bottomBar = {
            TheBottomNavigation(navController)

        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            chicViewModel.getProvidersByBusinessType(businessType)
            val providersUiState = chicViewModel.providerUiState
            val providersLazyListState = rememberLazyListState()
            when (providersUiState) {
                is ProvidersUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                is ProvidersUiState.Success -> ProvidersList(
                    chicViewModel = chicViewModel,
                    providers = providersUiState.providers,
                    providersLazyListState = providersLazyListState,
                    navController = navController
                )
                is ProvidersUiState.Error -> ErrorScreen(
                    retryAction,
                    modifier = modifier.fillMaxSize()
                )

            }
        }
    }
}



@Composable
fun ProviderCardListItem(
    provider: Provider,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier

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
                    .size(176.dp, 128.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(128.dp)

            ) {

                Text(
                    text = provider.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 48.dp)
                )
               /* StarProgressBar(
                    progressHairSalon = hairSalon,
                    totalStars = 5,
                    modifier = Modifier
                        .paddingFromBaseline(8.dp)
                )*/

            }

        }
    }
}

/*@Preview
@Composable
fun HairSalonCardListItemPreview() {
    HairSalonCardListItem(img = R.drawable.imagen_de_prueba, text = R.string.hair_salon_name)
}*/

@Composable
fun ProvidersList(
    chicViewModel: ChicViewModel,
    providers: List<Provider>,
    providersLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyColumn(modifier = modifier, state = providersLazyListState) {
        items(items = providers, key = { it.providerUser }) { provider ->
            ProviderCardListItem(
                provider = provider
            ) {
                // Guarda el proveedor en "AppointmentUiState" para cuando se vaya a reservar la cita
                chicViewModel.saveProviderViewModel(provider)
                // Navega a la pantalla de detalles del proveedor cuando se haga clic
                navController.navigate("providerDetails/${provider.providerUser}")
            }
        }
    }
}

/*@Preview
@Composable
fun HairSalonsListPreview() {
    HairSalonsList(hairSalons =, hairSalonsLazyListState =, navigateToDetail =)
}*/

@Composable
fun ProviderSearchBar(
    chicViewModel: ChicViewModel,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                MaterialTheme.colorScheme.background,
                CircleShape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search_barbershops),
            modifier = Modifier.padding(start = 16.dp),
        )
        Text(
            text = stringResource(id = R.string.search_barbershops),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
        //TODO: colocar este icono en todas las screen cuando el user se registre
        ProfileImage(
            chicViewModel= chicViewModel,
            description = stringResource(id = R.string.profile),
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp)
        )
    }
}

/*@Preview
@Composable
fun HairSalonSearchBarPreview() {
    HairSalonSearchBar()
}*/
/*

@Composable
fun StarProgressBar(progressHairSalon: Provider, totalStars: Int = 5, modifier: Modifier) {

    Row {
        repeat(totalStars) { index ->
            val starModifier = Modifier.width(24.dp) // Ajusta el tamaño según tus preferencias

            if (index < progressHairSalon.progress) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_star_rate_24), // Estrella llena para el progreso alcanzado
                    contentDescription = null,
                    modifier = starModifier
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.baseline_star_outline_24), // Estrella vacía para el progreso restante
                    contentDescription = null,
                    modifier = starModifier
                )
            }

            Spacer(modifier = Modifier.width(4.dp)) // Espaciado entre las estrellas (ajusta según tus preferencias)
        }
    }
}

@Preview
@Composable
fun StarProgressBarPreview() {
    //  StarProgressBar(Modifier,3.5f, 5)
}
*/


