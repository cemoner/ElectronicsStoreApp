package com.example.fooddeliveryapp.authentication.login.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(passwordText:String, function:(String) -> Unit,loginFunction:() -> Unit){
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(value = passwordText,
        onValueChange = function,
        label = { Text("Password") },
        placeholder = { Text("Enter your password") },
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        leadingIcon = {
            Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = Color.Gray
        )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                loginFunction()
                keyboardController?.hide()
            }
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.padding(8.dp)
    )
}