package com.example.electronicsstoreapp.navigation.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.presentation.viewmodel.ShoppingCartViewModel
import androidx.compose.material3.MaterialTheme

@Composable
fun ShoppingCart() {
    val viewModel: ShoppingCartViewModel = hiltViewModel()

    FloatingActionButton(
        onClick = { viewModel.tryNavigateTo(Destination.Cart()) },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
        modifier = Modifier.padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(18.dp).size(36.dp)
        )
    }
}
