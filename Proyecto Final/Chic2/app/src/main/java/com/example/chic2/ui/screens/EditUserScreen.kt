package com.example.chic2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.chic2.R
import com.example.chic2.network.User

@Composable
fun EditUserScreen(user: User?) {
    var editedUser by remember { mutableStateOf(user) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            //TODO: Asignar un recurso string al texto.
            text = ("Editar datos de usuario"),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditTextField(
            label = stringResource(id = R.string.name_user),
            text = editedUser.nameUser,
            onTextChanged = { editedUser = editedUser.copy(nameUser = it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditTextField(
            label = stringResource(id = R.string.password),
            text = editedUser.password ?: "",
            onTextChanged = { editedUser = editedUser.copy(password = it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditTextField(
            label = stringResource(id = R.string.email),
            text = editedUser.email,
            onTextChanged = { editedUser = editedUser.copy(email = it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditTextField(
            label = stringResource(id = R.string.phone),
            text = editedUser.phone,
            onTextChanged = { editedUser = editedUser.copy(phone = it) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = {}) {
                Text(stringResource(R.string.cancel).uppercase())
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { }
            ) {
                Text(stringResource(id = R.string.accept))
            }

        }

    }



}


@Composable
private fun EditTextField(
    label: String,
    text: String,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
        modifier = Modifier.fillMaxWidth()
    )
}

/*@Preview
@Composable
fun PreviewEditUserScreen() {
    EditUserScreen(
        user = User(
            user = "username",
            password = "password",
            email = "user@example.com",
            phone = "555-123-4567",
            nameUser = "User Name",
            address = Address(
                street = "123 Main St",
                city = "Cityville",
                state = "State",
                postalCode = "12345"
            )
        ),
        onSaveClicked = { *//* Handle save clicked *//* }
    )
}*/

/*chicViewModel.getUser("usuario1")
     val userUiState = chicViewModel.userUiState
     val appointmentsLazyListState = rememberLazyListState()
     when (userUiState) {
         is UserUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
         is UserUiState.Success -> UserAppointments(
             user = userUiState.user
         )

         is UserUiState.Error -> ErrorScreen(
             retryAction,
             modifier = Modifier.fillMaxSize()
         )
     }*/