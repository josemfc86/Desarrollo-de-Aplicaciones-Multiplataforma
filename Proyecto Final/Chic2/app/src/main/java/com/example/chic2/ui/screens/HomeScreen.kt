package com.example.chic2.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chic2.ChicScreen
import com.example.chic2.R
import com.example.chic2.ui.theme.Purple80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1
                    )
                }
            )
        },
        bottomBar = {
            TheBottomNavigation(navController)

        },
    ) { innerPadding ->
        CraftCardsCollectionsGrid(navController, Modifier.padding(innerPadding))
    }
}

/*@Preview
@Composable
fun ScaffoldHomePreview() {
    HomeScreen()
}*/


@Composable
fun TheBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                )
            },
            label = {
                // Text(text = stringResource(id = R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {navController.navigate(ChicScreen.Home.name)})

        /*NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                )
            },
            label = {
                // Text(text = stringResource(id = R.string.bottom_navigation_profile))
            },
            selected = true,
            onClick = {})*/

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                )
            },
            label = {
                // Text(text = stringResource(id = R.string.bottom_navigation_profile))
            },
            selected = true,
            onClick = {navController.navigate(ChicScreen.Signing.name)})

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                    modifier = modifier
                        .size(40.dp)
                )
            },
            label = {
                // Text(text = stringResource(id = R.string.bottom_navigation_profile))
            },
            selected = true,
            onClick = {})
    }
}

//Pantalla que muestra el mensaje de carga.
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

//Pantalla que muestra mensaje de error con botón de reintento.
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun CraftCard(
    @DrawableRes img: Int,
    @StringRes text: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(1) }

    when(text){
        R.string.hair_salons -> value = 1
        R.string.barbershop -> value = 2
        R.string.nail_salons -> value = 3
    }

   /* Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier,
       // color = MaterialTheme.colorScheme.surfaceVariant
    ){*/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)))
                .background(color = Purple80)
                .clickable {
                    when(value){
                        1 -> navController.navigate("providers/businessTypes/Hair_Salon")
                        2 -> navController.navigate("providers/businessTypes/Barbershop")
                        3 -> navController.navigate("providers/businessTypes/Nail_Salon")
                    }
                }

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
                )
            }
        }
   // }
}

/*@Preview(
    showBackground = true
)
@Composable

fun CraftCardPreview() {
    CraftCard(
        img = R.drawable.hair_salons_picture, text = R.string.hair_salons,

    )
}*/


@Composable
fun CraftCardsCollectionsGrid(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxHeight()
    ) {
        items(CraftCardsCollectionsData) { item ->
            CraftCard(
                item.drawable,
                item.text,
                navController
            )
        }
    }
}

/*@Preview(
    showBackground = true
)
@Composable
fun CraftCardsCollectionsGridPreview() {
    CraftCardsCollectionsGrid()
}*/

private val CraftCardsCollectionsData = listOf(
    R.drawable.hair_salons_picture to R.string.hair_salons,
    R.drawable.barbershops_picture to R.string.barbershop,
    R.drawable.nail_salons_picture to R.string.nail_salons,
    R.drawable.spas_esthetics_picture to R.string.spas_esthetics,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private val ScreenOptions: Map<Int, String> = mapOf(
    R.string.app_name to ChicScreen.Home.name,
    R.string.hair_salons to ChicScreen.Providers.name
)


