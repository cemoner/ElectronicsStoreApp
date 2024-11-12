    package com.example.fooddeliveryapp.main

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.animation.AnimatedContentTransitionScope
    import androidx.compose.animation.core.tween
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.systemBarsPadding
    import androidx.compose.material3.FabPosition
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Modifier
    import androidx.navigation.NavType
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController
    import androidx.navigation.navArgument
    import com.example.fooddeliveryapp.authentication.login.presentation.util.IsLoggedInSingleton
    import com.example.fooddeliveryapp.authentication.login.presentation.composable.LoginScreen
    import com.example.fooddeliveryapp.authentication.login.presentation.composable.RegisterScreen
    import com.example.fooddeliveryapp.home.presentation.composable.HomePageScreen
    import com.example.fooddeliveryapp.shared.navigation.model.NavItem
    import com.example.fooddeliveryapp.shared.navigation.presentation.composable.BottomNavigationBar
    import com.example.fooddeliveryapp.shared.navigation.presentation.composable.ShoppingCart
    import com.example.fooddeliveryapp.shared.profile.presentation.composable.ProfileScreen
    import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
    import dagger.hilt.android.AndroidEntryPoint


    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                FoodDeliveryAppTheme {
                    App(Modifier)
                }
            }
        }
    }


    @Composable
    fun App(modifier: Modifier) {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(modifier = Modifier.fillMaxSize().systemBarsPadding(),
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                if (shouldShowCart(currentRoute)) {
                    ShoppingCart(navController = navController)
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            },

        ) { innerPadding ->
            NavHost(
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(150),
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(150),
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(150),
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(150),
                    )
                },


                navController = navController,
                startDestination = NavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(NavItem.Home.route) {HomePageScreen(navController = navController)}
                composable(NavItem.Favorites.route) {}


                composable(
                    route = NavItem.Profile.route,
                    arguments = listOf(
                        navArgument("userId") {
                            defaultValue = "-1"
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: "-1"
                    ProfileScreen(navController, userId)
                }
                composable(NavItem.Login.route) {LoginScreen(navController)}
                composable(NavItem.Register.route){ RegisterScreen(navController) }
                composable(NavItem.Cart.route){}
            }
        }
    }

    @Composable
    fun shouldShowCart(currentRoute: String?): Boolean {
        return when (currentRoute) {
            "Home" -> true
            "Profile" -> IsLoggedInSingleton.getIsLoggedIn()
            else -> false
        }
    }