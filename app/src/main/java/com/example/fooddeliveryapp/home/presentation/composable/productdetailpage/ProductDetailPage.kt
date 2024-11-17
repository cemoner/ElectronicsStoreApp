package com.example.fooddeliveryapp.home.presentation.composable.productdetailpage

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.UiState
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.UiAction
import com.example.fooddeliveryapp.home.presentation.contracts.ProductDetailPageContract.SideEffect
import com.example.fooddeliveryapp.home.presentation.model.ProductUI
import com.example.fooddeliveryapp.home.presentation.viewmodel.ProductDetailPageViewModel
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

    val product: ProductUI = uiState.product!!

    val screenWidth = LocalConfiguration.current.screenWidthDp

    Scaffold(topBar = { TopBar(uiState,onAction) }) { innerPadding ->
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
                Box(modifier = Modifier.padding(4.dp).background(colorResource(R.color.white)).fillMaxWidth().clip(RoundedCornerShape(50)))
                {
                    Text(text = product.description, style = MaterialTheme.typography.bodySmall, modifier = Modifier.fillMaxWidth().padding(12.dp), textAlign = TextAlign.Justify)
                }
            }
            CountControl(uiState.count,onAction)
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
            IconButton(onClick = {onAction(UiAction.AddToFavoritesClicked)})  {
                Icon(Icons.Default.Favorite, contentDescription = "Back", tint = Color.Gray)
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
fun CountControl(count:Int, onAction:(UiAction) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = colorResource(R.color.purple_500))
            .height(48.dp)
            .fillMaxWidth(0.4f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick ={ onAction(UiAction.OnDecrement)},
            modifier = Modifier
                .size(40.dp)
                .background(colorResource(R.color.purple_500), shape = RoundedCornerShape(8.dp)) //
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrement",
                tint = Color.White
            )
        }

        Text(
            text = count.toString(),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(
            onClick = {onAction(UiAction.OnIncrement)},
            modifier = Modifier
                .size(40.dp) // Set the size of the IconButton
                .background(colorResource(R.color.purple_500), shape = RoundedCornerShape(8.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increment",
                tint = Color.White
            )
        }
    }
}