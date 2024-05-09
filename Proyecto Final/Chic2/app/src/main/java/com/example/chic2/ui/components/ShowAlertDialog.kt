package com.example.chic2.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    onConfirmClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(
                onClick = onConfirmClicked
            ) {
                Text("Aceptar")
            }
        }
    )
}