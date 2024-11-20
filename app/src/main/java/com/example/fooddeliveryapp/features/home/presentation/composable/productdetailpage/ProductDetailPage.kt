package com.example.fooddeliveryapp.features.home.presentation.composable.productdetailpage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiState
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.UiAction
import com.example.fooddeliveryapp.features.home.presentation.contract.ProductDetailPageContract.SideEffect
import com.example.fooddeliveryapp.common.presentation.model.entity.ProductUI
import com.example.fooddeliveryapp.features.home.presentation.viewmodel.ProductDetailPageViewModel
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductDetailPage(){
    val viewModel: ProductDetailPageViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    ProductDetailPageContent(uiState,onAction,sideEffect)
}





@Composable
fun ProductDetailPageContent(uiState: UiState, onAction:(UiAction) -> Unit, sideEffect: Flow<SideEffect>){

    val product: ProductUI = uiState.product
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    Scaffold(
        topBar = { TopBar(uiState,onAction) },
        bottomBar = { BottomBar(uiState,onAction) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(top = 18.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RatingView(product.rate)
            Spacer(modifier = Modifier.height(12.dp))
            AsyncImage(
            model = product.image,
            contentDescription = "Product Image",
                modifier = Modifier
                    .width((screenWidth.dp * 0.5f)).height(200.dp).padding(5.dp),

            contentScale = ContentScale.Crop
        )
            Row(modifier = Modifier.padding(2.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = product.category, style = MaterialTheme.typography.titleMedium)
            }
            Row(modifier = Modifier.padding(12.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = product.price.toString() + " ₺", style = MaterialTheme.typography.titleLarge)
            }
            Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                Text("Description:",style = MaterialTheme.typography.titleSmall)
                Box(modifier = Modifier.padding(4.dp).background(colorResource(R.color.white)).fillMaxWidth())
                {
                    Text(text = product.description, style = MaterialTheme.typography.bodySmall, modifier = Modifier.fillMaxWidth().padding(12.dp), textAlign = TextAlign.Justify)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(uiState: UiState,onAction:(UiAction) -> Unit){
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Product Detail",
                color = Color.Black,
                fontSize = 20.sp
            )
        } },


        navigationIcon = {
            IconButton(onClick = {onAction(UiAction.BackClicked)})  {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Gray)
            }
        },
        actions = {
            IconButton(onClick = {onAction(UiAction.AddToFavoritesClicked(uiState.product.id))})  {
                Icon(Icons.Default.Favorite, contentDescription = "Back", tint = (colorResource(R.color.purple_500)))
            }
        }
    )

}

@Composable
fun RatingView(rating: Double, maxRating: Int = 5) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5

    Row() {
        for (i in 1..maxRating) {
            when {
                i <= fullStars -> {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Full star",
                        tint = Color.Yellow
                    )
                }
                i == fullStars + 1 && hasHalfStar -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.StarHalf,
                        contentDescription = "Half star",
                        tint = Color.Yellow
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = "Empty star",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}


@Composable
fun BottomBar(uiState: UiState, onAction: (UiAction) -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if(uiState.product.salePrice == 0.0) {
                    "Price: ${uiState.product.price} ₺"
                }
                    else {
                        "Price: ${uiState.product.salePrice} ₺"
                         }
                ,
                modifier = Modifier.weight(1f).fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { onAction(UiAction.AddToCartClicked(uiState.product.id)) },
                shape = RectangleShape,
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_500)
                )
            ) {
                Text("Add To Cart", color = Color.White, modifier = Modifier.fillMaxWidth().padding(10.dp), fontSize = 20.sp, textAlign = TextAlign.Center)
            }
        }
}