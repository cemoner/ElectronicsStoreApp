package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(
    passwordText: String,
    function: (String) -> Unit,
    loginFunction: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = passwordText,
        onValueChange = function,
        label = { Text("Password") },
        placeholder = { Text("Enter your password") },
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
        leadingIcon = {
            Icon(
                Icons.Default.Key,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.surfaceTint,
            )
        },
        keyboardOptions =
            KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
            ),
        keyboardActions =
            KeyboardActions(
                onDone = {
                    loginFunction()
                    keyboardController?.hide()
                },
            ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.padding(8.dp),
    )
}
