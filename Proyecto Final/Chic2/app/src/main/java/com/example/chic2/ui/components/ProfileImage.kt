package com.example.chic2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chic2.R
import com.example.chic2.ui.screens.ChicViewModel

@Composable
fun ProfileImage(
    chicViewModel: ChicViewModel,
    description: String,
    modifier: Modifier = Modifier
) {

    val imagePainter = if (chicViewModel.uiState.value.user != null) {
       painterResource(id = R.drawable.avatar)
    } else {
        painterResource(id = R.drawable.avatar_signing)
    }

    Image(
        painter = imagePainter,
        contentDescription = description,
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
    )
}