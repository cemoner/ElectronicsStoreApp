package com.example.electronicsstoreapp.features.profile.profile.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.SideEffect
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.UiAction
import com.example.electronicsstoreapp.features.profile.profile.presentation.contract.ProfileContract.UiState
import com.example.electronicsstoreapp.features.profile.profile.presentation.viewmodel.ProfileViewModel
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

data class ButtonItem(
    val displayName: String,
    val camelCaseName: String,
    val icon: ImageVector,
    val uiAction: UiAction,
)

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val (uiState, uiAction, sideEffect) = viewModel.unpack()
    ProfileContent(uiState, uiAction, sideEffect)
}

@Composable
fun ProfileContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
) {
    val context = LocalContext.current

    val buttonNames =
        mapOf(
            "Change Password" to ButtonItem("Change Password", "changePassword", Icons.Default.Password, UiAction.OnChangePassword),
            "Address Management" to ButtonItem("Address Management", "addressManagement", Icons.Default.Home, UiAction.OnAddressManagement),
            "Favorites" to ButtonItem("Favorites", "favorites", Icons.Default.Favorite, UiAction.OnFavorites),
            "Logout" to ButtonItem("Logout", "logout", Icons.AutoMirrored.Filled.Logout, UiAction.OnLogoutButtonClicked),
        )

    CollectSideEffect(sideEffect) {
        when (it) {
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        modifier =
            Modifier
                .padding(horizontal = 32.dp, vertical = 12.dp)
                .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                ProfileInfoCard(uiState)
            }
            items(buttonNames.entries.toList()) { buttonItem ->
                CreateButtons(
                    text = buttonItem.value.displayName,
                    onClick = { onAction(buttonItem.value.uiAction) },
                    icon = buttonItem.value.icon,
                )
            }
        }
    }
}

@Composable
fun ProfileInfoCard(uiState: UiState) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(28.dp).fillMaxWidth()) {
                Text(
                    text = "Name: ${uiState.name}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(5.dp),
                )
                Text(
                    text = "Email: ${uiState.email}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(5.dp),
                )
                Text(
                    text = "Phone: ${uiState.phoneNumber}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(5.dp),
                )
            }
        }
    }
}

@Composable
fun CreateButtons(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$text + Icon",
                    modifier = Modifier.size(24.dp).padding(end = 8.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
                Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
