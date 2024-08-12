package com.example.fooddeliveryapp.authentication.components

import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension

@Composable
fun Password(passwordText:String,function:(String) -> Unit,modifier: Modifier){
    TextField(value = passwordText,
        onValueChange = function,
        label = { Text("Password") },
        placeholder = { Text("Enter your password") },
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent, // Removes the underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Removes the underline when not focused
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
            imeAction = ImeAction.Done, // Sets the action button on the keyboard to "Done"
            keyboardType = KeyboardType.Password // Displays a password keyboard, usually with masked input
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}