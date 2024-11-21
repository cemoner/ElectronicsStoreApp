package com.example.electronicsstoreapp.navigation.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.R
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.presentation.viewmodel.ShoppingCartViewModel

@Composable
fun ShoppingCart() {
    val viewmodel: ShoppingCartViewModel = hiltViewModel()
    FloatingActionButton(
        onClick = { viewmodel.tryNavigateTo(Destination.Cart()) },
        shape = CircleShape,
        containerColor = colorResource(R.color.purple_500),
        elevation = FloatingActionButtonDefaults.elevation(2.dp,3.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart",
            tint = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .size(36.dp)
        )
    }
}