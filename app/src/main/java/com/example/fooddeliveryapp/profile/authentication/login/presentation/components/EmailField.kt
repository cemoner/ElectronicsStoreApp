package com.example.fooddeliveryapp.profile.authentication.login.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailTextField(emailText:String, function: (String) -> Unit){
    TextField(
        value = emailText,
        onValueChange =  function ,
        label = { Text("E-mail") },
        placeholder = { Text("Enter your e-mail") },
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        leadingIcon = {
            Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = Color.Gray
        )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, // Sets the action button on the keyboard to "Done"
            keyboardType = KeyboardType.Email // Displays a password keyboard, usually with masked input
        ),
        modifier = Modifier.padding(8.dp)
    )
}