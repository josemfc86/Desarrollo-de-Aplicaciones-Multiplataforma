package com.example.chic2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.chic2.R
import com.example.chic2.network.User

@Composable
fun ButtonsGroup(
    onCancelButtonClicked: () -> Unit,
    button: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            OutlinedButton(onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel).uppercase())
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            button()
        }

    }
}

@Composable
fun NextButton(
    onNextButtonClicked: (MutableSet<Long>) -> Unit,
    selectedServicesIds: MutableSet<Long>,
    modifier: Modifier = Modifier
) {
    Button(
        // el botón se activa cuando el usuario realiza una selección
        enabled = selectedServicesIds.isNotEmpty(),
        onClick = { onNextButtonClicked(selectedServicesIds) }
    ) {
        Text(stringResource(id = R.string.next))
    }
}

@Composable
fun BookingButton(
    formattedDateTime: String,
    onBookButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        // el botón se activa cuando el usuario realiza una selección
        enabled = formattedDateTime != "",
        onClick = { onBookButtonClicked() }
    ) {
        Text(stringResource(id = R.string.next))
    }
}

@Composable
fun SigningButton(
    onSigningButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onSigningButtonClicked() }
    ) {
        Text(stringResource(id = R.string.signing))
    }
}

@Composable
fun logingButton(
    user: String,
    password: String,
    onLogingButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onLogingButtonClicked(user, password) }
    ) {
        Text(stringResource(id = R.string.accept))
    }
}