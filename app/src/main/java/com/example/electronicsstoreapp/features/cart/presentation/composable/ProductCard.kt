package com.example.electronicsstoreapp.features.cart.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.electronicsstoreapp.common.presentation.model.ProductUI
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.UiAction

@Composable
fun ProductCard(
    product: ProductUI,
    onAction: (UiAction) -> Unit,
) {
    Card(
        onClick = { onAction(UiAction.OnProductClicked(product.id,product.category)) },
        enabled = true,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            AsyncImage(
                model = product.image1,
                contentDescription = "Product Image",
                modifier = Modifier.padding(5.dp).weight(0.4f),
            )
            Column(modifier = Modifier.padding(8.dp).fillMaxHeight().weight(0.4f), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = product.title, style = MaterialTheme.typography.bodyLarge)
                Text(text = "${product.price} ₺", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onAction(UiAction.DeleteFromCart(product.id)) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
