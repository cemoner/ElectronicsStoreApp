package com.example.fooddeliveryapp.features.cart.presentation.composable

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.features.cart.presentation.contract.CartPageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI

@Composable
fun ProductCard(product: ProductUI, onAction:(UiAction) -> Unit) {
    Card(
        onClick = { onAction(UiAction.OnProductClicked(product.id))},
        enabled = true,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row(){
            AsyncImage(
                model = product.image,
                contentDescription = "Product Image",
                modifier = Modifier.padding(5.dp).weight(0.4f),
            )
            Column(modifier = Modifier.padding(8.dp).fillMaxHeight().weight(0.4f), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = product.title)
                Text(text = product.price.toString())

            }
            IconButton(onClick = {onAction(UiAction.DeleteFromCart(product.id))}) {
                Icon(Icons.Default.Delete, contentDescription = "Delete",tint = colorResource(R.color.purple_500))
            }
        }
    }
}