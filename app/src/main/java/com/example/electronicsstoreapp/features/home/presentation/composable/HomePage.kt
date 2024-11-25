package com.example.electronicsstoreapp.features.home.presentation.composable
import android.widget.Toast
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.R
import com.example.electronicsstoreapp.common.presentation.composable.ProductCard
import com.example.electronicsstoreapp.features.home.presentation.viewmodel.HomePageViewModel
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.UiState
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.UiAction
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageContract.SideEffect
import com.example.electronicsstoreapp.features.home.presentation.contract.HomePageToolBarUiState
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun HomePageScreen() {
    val viewModel: HomePageViewModel = hiltViewModel()
    val (uiState,onAction,sideEffect) = viewModel.unpack()
    HomePageContent(uiState,onAction,sideEffect)
}

@Composable
fun HomePageContent(uiState:UiState, onAction: (UiAction) -> Unit, sideEffect: Flow<SideEffect>){
    val context = LocalContext.current
    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        topBar = {  TopBar(uiState,onAction) },
        content = { padding ->
            Column(modifier = Modifier.
                padding(padding)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                ) {
                    when(uiState){
                        is UiState.Success -> {
                            items(uiState.searchProducts) { product ->
                                ProductCard(
                                    product = product,
                                    onProductClicked = { productId ->
                                        onAction(UiAction.OnProductClicked(productId))
                                    },
                                    onFavoritesClicked = { productId ->
                                        onAction(UiAction.OnFavoritesButtonClicked(productId))
                                    }
                                )
                            }
                        }
                        UiState.Error -> {

                        }
                        UiState.Loading -> {
                            items(8){
                                Column(
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .defaultMinSize(minHeight = 250.dp)
                                        .fillMaxWidth(
                                        )
                                ) {
                                    Box(
                                        modifier = Modifier.defaultMinSize(minHeight = 150.dp).fillMaxWidth().shimmerEffect().clip(
                                            RoundedCornerShape(6.dp)
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Box(
                                        modifier = Modifier.defaultMinSize(minHeight = 20.dp).fillMaxWidth().shimmerEffect()
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Box(
                                        modifier = Modifier.defaultMinSize(minHeight = 20.dp).fillMaxWidth().shimmerEffect()
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Box(
                                        modifier = Modifier.defaultMinSize(minHeight = 20.dp).fillMaxWidth().shimmerEffect()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val startOffSetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1000)
        ), label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                MaterialTheme.colorScheme.surface
            ),
            start = Offset(startOffSetX, 0f),
            end = Offset(startOffSetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}


@Composable
fun SearchBar(uiState: UiState, onAction: (UiAction) -> Unit) {
    if (uiState !is UiState.Success) return
    if (uiState.toolBarUiState is HomePageToolBarUiState.Search) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .clip(RoundedCornerShape(18.dp)),
            value = uiState.toolBarUiState.searchText,
            onValueChange = {
                onAction(UiAction.OnSearchTextChange(it))
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            placeholder = {
                Text(
                    text = "Search",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
        )
    }
}



@Composable
fun TopBar(uiState: UiState, onAction: (UiAction) -> Unit) {
    if (uiState !is UiState.Success) return
    when(uiState.toolBarUiState){
        is HomePageToolBarUiState.DefaultToolBar -> DefaultTopBar(uiState, onAction)
        is HomePageToolBarUiState.Search -> SearchActiveTopBar(uiState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(uiState: UiState, onAction: (UiAction) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Home",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        actions = {
            IconButton(onClick = { onAction(UiAction.OnToolBarStateChange) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchActiveTopBar(uiState: UiState, onAction: (UiAction) -> Unit) {
    TopAppBar(
        title = { SearchBar(uiState, onAction) },
        navigationIcon = {
            IconButton(onClick = {
                onAction(UiAction.OnToolBarStateChange)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
