package com.example.fooddeliveryapp.features.home.presentation.composable.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.features.home.presentation.contract.HomePageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.model.ProductUI

@Composable
fun ProductCard(product: ProductUI, onAction:(UiAction) -> Unit){
    Card(
        onClick = { onAction(UiAction.ProductClicked(product.id))},
        enabled = true,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).defaultMinSize(minHeight = 315.dp)
        ){
            Column(){
                AsyncImage(
                    model = product.image,
                    contentDescription = "Product Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = product.title, style = MaterialTheme.typography.titleMedium, maxLines = 3)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = product.category,style = MaterialTheme.typography.titleSmall)
                Text(text = product.rate.toString() + "/5", style = MaterialTheme.typography.titleSmall)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = product.price.toString() +  " ₺", style = MaterialTheme.typography.titleMedium)
                IconButton(
                    onClick = {onAction(UiAction.OnAddClicked(product.id)) },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.purple_500))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
        }

    }
}