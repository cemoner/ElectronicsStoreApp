package com.example.electronicsstoreapp.main.composable

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.electronicsstoreapp.features.cart.presentation.composable.CartPageScreen
import com.example.electronicsstoreapp.features.home.presentation.composable.FavoritesPageScreen
import com.example.electronicsstoreapp.features.home.presentation.composable.HomePageScreen
import com.example.electronicsstoreapp.features.home.presentation.composable.ProductDetailPage
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.composable.LoginScreen
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.composable.RegisterScreen
import com.example.electronicsstoreapp.features.profile.profile.presentation.composable.ProfileScreen
import com.example.electronicsstoreapp.main.contract.MainContract.SideEffect
import com.example.electronicsstoreapp.main.contract.MainContract.UiAction
import com.example.electronicsstoreapp.main.contract.MainContract.UiState
import com.example.electronicsstoreapp.main.viewmodel.MainViewModel
import com.example.electronicsstoreapp.mvi.unpack
import com.example.electronicsstoreapp.navigation.model.Destination
import com.example.electronicsstoreapp.navigation.navigator.NavigationIntent
import com.example.electronicsstoreapp.navigation.presentation.composable.NavHost
import com.example.electronicsstoreapp.navigation.presentation.composable.NavigationBar
import com.example.electronicsstoreapp.navigation.presentation.composable.ShoppingCart
import com.example.electronicsstoreapp.navigation.presentation.composable.composable
import com.example.electronicsstoreapp.ui.theme.ElectronicsStoreAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawableResource(android.R.color.transparent)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                return@setKeepOnScreenCondition false
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElectronicsStoreAppTheme(darkTheme = false) {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppContent(uiState, onAction, sideEffect, viewModel)
    }
}

@Composable
fun AppContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
    viewModel: MainViewModel,
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController,
        onAction,
    )

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val route = destination.route
            val shouldShowBottomBar =
                route == Destination.Home() || route == Destination.Favorites() || route?.startsWith("profile") == true
            onAction(UiAction.OnChangeShowBottomBar(shouldShowBottomBar))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).systemBarsPadding(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (uiState.showBottomBar) {
                ShoppingCart()
            }
        },
        bottomBar = {
            if (uiState.showBottomBar) {
                NavigationBar(navController)
            }
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destination.Home,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(destination = Destination.Home) {
                HomePageScreen()
            }
            composable(destination = Destination.Profile) {
                ProfileScreen()
            }
            composable(destination = Destination.Login) {
                LoginScreen()
            }
            composable(destination = Destination.Register) {
                RegisterScreen()
            }
            composable(destination = Destination.Favorites) {
                FavoritesPageScreen()
            }

            composable(destination = Destination.Cart) {
                CartPageScreen()
            }

            composable(destination = Destination.ProductDetail) {
                ProductDetailPage()
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController,
    onAction: (UiAction) -> Unit,
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) {
                                saveState = intent.saveState
                                inclusive = intent.inclusive
                            }
                        }
                        restoreState = intent.restoreState
                    }
                }
                is NavigationIntent.ClearBackStack -> {
                    navHostController.popBackStack(navHostController.graph.id, false)
                    navHostController.navigate(Destination.Home())
                }
            }
        }
    }
}
