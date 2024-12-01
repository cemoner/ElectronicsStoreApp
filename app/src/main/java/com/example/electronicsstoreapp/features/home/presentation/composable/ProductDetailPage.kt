package com.example.electronicsstoreapp.features.home.presentation.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.size.Size
import com.example.electronicsstoreapp.common.presentation.model.ProductUI
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.SideEffect
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.ProductDetailPageContract.UiState
import com.example.electronicsstoreapp.features.home.presentation.model.CarouselItem
import com.example.electronicsstoreapp.features.home.presentation.viewmodel.ProductDetailPageViewModel
import com.example.electronicsstoreapp.main.util.FavoritesSingleton
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailPage() {
    val viewModel: ProductDetailPageViewModel = hiltViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    ProductDetailPageContent(uiState, onAction, sideEffect)
}

@ExperimentalMaterial3Api
@Composable
fun ProductDetailPageContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
) {
    val product: ProductUI = uiState.product
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { uiState.images.size })

    CollectSideEffect(sideEffect) {
        when (it) {
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = { TopBar(uiState, onAction) },
        bottomBar = { BottomBar(uiState, onAction) },
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ) { innerPadding ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(top = 18.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) { page ->
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            AsyncImage(
                                model =
                                    ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(uiState.images[page])
                                        .crossfade(true)
                                        .allowHardware(false)
                                        .size(Size.ORIGINAL)
                                        .build(),
                                contentDescription = "Product Image",
                                contentScale = ContentScale.FillWidth,
                                modifier =
                                    Modifier
                                        .fillMaxWidth(0.8f),
                            )
                        }
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        pageCount = uiState.images.size,
                        modifier = Modifier.padding(16.dp),
                        activeColor = MaterialTheme.colorScheme.primary,
                        inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        spacing = 6.dp,
                        indicatorShape = CircleShape,
                    )
                }
            }
            item {
                Row(
                    modifier =
                        Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = product.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            item {
                Row(
                    modifier =
                        Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "${product.price} ₺",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            item { RatingView(product.rate) }
            item {
                Row(
                    modifier =
                        Modifier
                            .padding(vertical = 4.dp, horizontal = 12.dp)
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Description:",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Box(
                        modifier =
                            Modifier
                                .padding(4.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .fillMaxWidth(),
                    ) {
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodySmall,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(24.dp)
                ){
                    Text("Similar Products")
                    CarouselContent(
                        carouselItems = uiState.carouselItems,
                        onAction = onAction
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselContent(carouselItems:List<CarouselItem>,onAction: (UiAction) -> Unit){
    if(carouselItems.isNotEmpty()){
        Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            HorizontalMultiBrowseCarousel(
                state = rememberCarouselState { carouselItems.size },
                itemSpacing = 12.dp,
                contentPadding = PaddingValues(start = 12.dp),
                preferredItemWidth = 250.dp,
            ) { index ->
                Card(modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    onClick = {onAction(UiAction.OnProductClicked(carouselItems[index].id,carouselItems[index].category))}
                ){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(carouselItems[index].image)
                            .crossfade(true)
                            .allowHardware(false)
                            .size(Size.ORIGINAL)
                            .listener(
                                onSuccess = { _, _ -> Log.d("Coil", "Image loaded successfully for page: $index") },
                                onError = { _, throwable -> Log.e("Coil", "Image loading failed", throwable.throwable) }
                            )
                            .build(),
                        contentDescription = "Product Image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )

                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Product Detail",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onAction(UiAction.OnBackButtonClicked) }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        actions = {
            IconButton(onClick = { onAction(UiAction.OnFavoritesButtonClicked(uiState.product.id)) }) {
                Icon(
                    imageVector = if (FavoritesSingleton.isFavorite(uiState.product.id)) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = if (FavoritesSingleton.isFavorite(uiState.product.id)) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
    )
}

@Composable
fun RatingView(
    rating: Double,
    maxRating: Int = 5,
) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5

    Row {
        for (i in 1..maxRating) {
            when {
                i <= fullStars -> {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Full star",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
                i == fullStars + 1 && hasHalfStar -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.StarHalf,
                        contentDescription = "Half star",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = "Empty star",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text =
                if (uiState.product.salePrice == 0.0) {
                    "Price: ${uiState.product.price} ₺"
                } else {
                    "Price: ${uiState.product.salePrice} ₺"
                },
            modifier = Modifier.weight(1f).fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Button(
            onClick = { onAction(UiAction.AddToCartButtonClicked(uiState.product.id)) },
            shape = RectangleShape,
            modifier =
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp)),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
        ) {
            Text(
                "Add To Cart",
                color = Color.White,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}
