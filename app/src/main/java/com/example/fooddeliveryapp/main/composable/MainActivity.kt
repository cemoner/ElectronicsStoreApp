    package com.example.fooddeliveryapp.main.composable

    import android.app.Activity
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.systemBarsPadding
    import androidx.compose.material3.FabPosition
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.platform.LocalContext
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.rememberNavController
    import com.example.fooddeliveryapp.features.cart.presentation.composable.CartPageScreen
    import com.example.fooddeliveryapp.features.home.presentation.composable.FavoritesPageScreen
    import com.example.fooddeliveryapp.features.home.presentation.composable.HomePageScreen
    import com.example.fooddeliveryapp.features.home.presentation.composable.ProductDetailPage
    import com.example.fooddeliveryapp.main.contract.MainContract.UiState
    import com.example.fooddeliveryapp.main.contract.MainContract.UiAction
    import com.example.fooddeliveryapp.main.contract.MainContract.SideEffect
    import com.example.fooddeliveryapp.main.viewmodel.MainViewModel
    import com.example.fooddeliveryapp.mvi.unpack
    import com.example.fooddeliveryapp.navigation.model.Destination
    import com.example.fooddeliveryapp.navigation.navigator.NavigationIntent
    import com.example.fooddeliveryapp.navigation.presentation.composable.NavHost
    import com.example.fooddeliveryapp.navigation.presentation.composable.NavigationBar
    import com.example.fooddeliveryapp.navigation.presentation.composable.ShoppingCart
    import com.example.fooddeliveryapp.navigation.presentation.composable.composable
    import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.composable.LoginScreen
    import com.example.fooddeliveryapp.features.profile.authentication.login.presentation.composable.RegisterScreen
    import com.example.fooddeliveryapp.features.profile.profile.presentation.composable.ProfileScreen
    import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.channels.Channel
    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.receiveAsFlow


    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                FoodDeliveryAppTheme {
                    AppScreen()
                }
            }
        }
    }


    @Composable
    fun AppScreen(
    ){
        val viewModel:MainViewModel = hiltViewModel()
        val (uiState,onAction,sideEffect) = viewModel.unpack()
        AppContent(uiState,onAction,sideEffect,viewModel)
    }


    @Composable
    fun AppContent(
        uiState: UiState,
        onAction: (UiAction) -> Unit,
        sideEffect: Flow<SideEffect>,
        viewModel: MainViewModel
    ) {
        val navController = rememberNavController()

        NavigationEffects(
            navigationChannel = viewModel.navigationChannel,
            navHostController = navController,
            onAction
        )

        
        LaunchedEffect(navController) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val route = destination.route
                val shouldShowBottomBar = route == Destination.Home() || route == Destination.Favorites() || route?.startsWith("profile") == true
                onAction(UiAction.OnChangeShowBottomBar(shouldShowBottomBar))
            }
        }


        Scaffold(modifier = Modifier.fillMaxSize().systemBarsPadding(),
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                if(uiState.showBottomBar){
                    ShoppingCart()
                }
            },
            bottomBar = {
                if(uiState.showBottomBar){
                    NavigationBar(navController)
                }
            },
        ){ innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Destination.Home,
                modifier = Modifier.padding(innerPadding)
            ){
                composable(destination = Destination.Home){
                    HomePageScreen()
                }
                composable(destination = Destination.Profile){
                    ProfileScreen()
                }
                composable(destination = Destination.Login){
                    LoginScreen()
                }
                composable(destination = Destination.Register){
                    RegisterScreen()
                }
                composable(destination = Destination.Favorites){
                    FavoritesPageScreen()
                }

                composable(destination = Destination.Cart){
                    CartPageScreen()
                }

                composable(destination = Destination.ProductDetail){
                    ProductDetailPage()
                }
            }
        }
    }


    @Composable
    fun NavigationEffects(
        navigationChannel:Channel<NavigationIntent>,
        navHostController: NavHostController,
        onAction: (UiAction) -> Unit
        ){
        val activity = (LocalContext.current as? Activity)
        LaunchedEffect(activity,navHostController,navigationChannel) {
            navigationChannel.receiveAsFlow().collect { intent ->
                if(activity?.isFinishing == true){
                    return@collect
                }
                when(intent){
                    is NavigationIntent.NavigateBack -> {
                        if(intent.route != null){
                            navHostController.popBackStack(intent.route,intent.inclusive)
                        }
                        else {
                            navHostController.popBackStack()
                        }
                    }
                    is NavigationIntent.NavigateTo -> {
                        navHostController.navigate(intent.route){
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
                }
            }
        }
    }