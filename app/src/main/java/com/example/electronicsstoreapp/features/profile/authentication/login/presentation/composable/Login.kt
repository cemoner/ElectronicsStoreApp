package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components.EmailTextField
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components.PasswordTextField
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components.RegOrLoginDuo
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.SideEffect
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.UiAction
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.contract.LoginContract.UiState
import com.example.electronicsstoreapp.features.profile.authentication.login.presentation.viewmodel.LoginViewModel
import com.example.electronicsstoreapp.mvi.CollectSideEffect
import com.example.electronicsstoreapp.mvi.unpack
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = hiltViewModel()
    val (uiState, onAction, sideEffect) = viewModel.unpack()
    LoginContent(uiState, onAction, sideEffect)
}

@Composable
fun LoginContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    sideEffect: Flow<SideEffect>,
) {
    val context = LocalContext.current

    CollectSideEffect(sideEffect) {
        when (it) {
            is SideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    Scaffold(
        topBar = { TopBar(onAction) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EmailTextField(
                emailText = uiState.email,
                function = { onAction(UiAction.OnEmailChange(it)) },
            )

            PasswordTextField(
                uiState.password,
                { onAction(UiAction.OnPasswordChange(it)) },
                { onAction(UiAction.OnLoginButtonClicked) },
            )
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = { onAction(UiAction.OnLoginButtonClicked) },
                shape = RoundedCornerShape(32.dp),
            ) {
                Text("Log In", modifier = Modifier.padding(7.5.dp), fontSize = 14.sp)
            }

            if (uiState.showProgress) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
            }
            RegOrLoginDuo(
                ({ onAction(UiAction.OnRegisterButtonClicked) }),
                textString = "Don't have an Account?",
                buttonString = "Register",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAction: (UiAction) -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onAction(UiAction.OnBackButtonClicked) }) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.surfaceTint,
                )
            }
        },
    )
}
