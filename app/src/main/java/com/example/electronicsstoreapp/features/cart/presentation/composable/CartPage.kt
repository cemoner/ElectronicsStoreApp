package com.example.electronicsstoreapp.features.cart.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.R
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.SideEffect
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.UiAction
import com.example.electronicsstoreapp.features.cart.presentation.contract.CartPageContract.UiState
import com.example.electronicsstoreapp.features.cart.presentation.viewmodel.CartPageViewModel
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow
import java.util.Locale

@Composable
fun CartPageScreen() {
    val viewModel = hiltViewModel<CartPageViewModel>()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    CartPageContent(uiState, onAction, sideEffect)
}

@Composable
fun CartPageContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
) {
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when (it) {
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = { TopBar(onAction) },
        bottomBar = { BottomBar(uiState, onAction) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (uiState.products.isEmpty()) {
                Text(
                    "Your cart is empty.",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.products.size) { index ->
                        ProductCard(uiState.products[index], onAction)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAction: (UiAction) -> Unit) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = "Cart",
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onAction(UiAction.BackClicked) }) {
                Icon(Icons.Default.Close, contentDescription = "Back", tint = Color.Gray)
            }
        },
    )
}

@Composable
fun BottomBar(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("Delivery Charge:")
            Text("0₺")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("Total Price: ${String.format(Locale.getDefault(), "%.3f₺", uiState.totalPrice)}")
        }
        Button(
            onClick = { onAction(UiAction.OnBuyClicked) },
            modifier =
                Modifier.fillMaxWidth().clip(
                    RoundedCornerShape(10),
                ),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
        ) {
            Text(
                "Place Order",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
            )
        }
    }
}
