package com.example.fooddeliveryapp.shared.profile.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.authentication.login.presentation.contracts.LoginContract
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiState
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.UiAction
import com.example.fooddeliveryapp.shared.profile.presentation.contracts.ProfileContract.SideEffect
import com.example.fooddeliveryapp.mvi.CollectSideEffect
import com.example.fooddeliveryapp.mvi.unpack
import com.example.fooddeliveryapp.shared.profile.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.Flow


data class ButtonItem(
    val displayName: String,
    val camelCaseName: String,
    val icon: ImageVector,
    val uiAction:UiAction
)

@Composable
fun ProfileScreen(
    navController: NavController,
    userId:String){

    val viewModel: ProfileViewModel = hiltViewModel()
    viewModel.checkIsLoggedIn()
    viewModel.getUserData(userId.replace("{","").replace("}",""))

    val (uiState,uiAction,sideEffect) = viewModel.unpack()
    ProfileContent(uiState,uiAction,sideEffect,navController,userId)
}


@Composable
fun ProfileContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
    navController: NavController,
    userId: String
){
    val whiteColor = colorResource(id = R.color.white)
    val context = LocalContext.current


    val buttonNames = mapOf(
        "Change Password" to ButtonItem("Change Password", "changePassword",Icons.Default.Password,UiAction.OnChangePassword),
        "Order History" to ButtonItem("Order History", "orderHistory", Icons.Default.History,UiAction.OnOrderHistory),
        "Address Management" to ButtonItem("Address Management", "addressManagement", Icons.Default.Home,UiAction.OnAddressManagement),
        "Favorites" to ButtonItem("Favorites", "favorites", Icons.Default.Favorite,UiAction.OnFavorites),
        "Logout" to ButtonItem("Logout", "logout", Icons.AutoMirrored.Filled.Logout,UiAction.OnLogout)
    )

    CollectSideEffect(sideEffect) {
        when(it){
            is SideEffect.Navigate -> {
                navController.navigate(it.route)
            }
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 12.dp).fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = whiteColor )
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(32.dp).fillMaxWidth()) {
                            Text(text = "Name: ${uiState.name}", color = Color.Black, modifier = Modifier.padding(5.dp))
                            Text(text = "Email: ${uiState.email}", color = Color.Black, modifier = Modifier.padding(5.dp))
                            Text(text = "Phone: ${uiState.phoneNumber}", color = Color.Black, modifier = Modifier.padding(5.dp))
                        }
                    }
                }
            }
            items(buttonNames.entries.toList()) { buttonItem ->
                CreateButtons(
                    text = buttonItem.value.displayName,
                    onClick = { onAction(buttonItem.value.uiAction) },
                    icon = buttonItem.value.icon,
                    whiteColor
                )
            }
        }
    }

}


@Composable
fun CreateButtons(text: String, onClick: () -> Unit, icon: ImageVector, whiteColor: Color) {

    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {


        Button(onClick = onClick, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 0.dp), colors = ButtonDefaults.buttonColors(whiteColor),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)){
            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
                Icon(
                    imageVector = icon,
                    contentDescription = "${text} + Icon" ,
                    modifier = Modifier.size(24.dp).padding(end = 2.dp),
                    tint = Color.Black
                )
                Text(text = text, color = Color.Black)
            }
        }
    }

}
