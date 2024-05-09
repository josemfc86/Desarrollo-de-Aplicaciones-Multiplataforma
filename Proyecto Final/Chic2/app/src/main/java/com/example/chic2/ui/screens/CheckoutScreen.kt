package com.example.chic2.ui.screens

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CheckoutScreen(selectedServices: String) {
    val idsList = selectedServices.split(",").map { it.toLong() }
    val idsLazyListState = rememberLazyListState()

/*
    Text(
        text = idsList.toString(),
        style = MaterialTheme.typography.bodyMedium
    )
    LazyColumn(modifier = Modifier, state = idsLazyListState) {

        items(items = idsList) { service ->
            ServiceListItem(
                service = service,
                selectedServices = selectedServices,
                onSelectionChange = { selected ->
                    if (selected) {
                        selectedServices.add(service)
                    } else {
                        selectedServices.remove(service)
                    }
                }
            )
        }

    }*/
}
