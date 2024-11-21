package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun FormTextFieldContent(text:String, function: (String) -> Unit, type:String){
    TextField(value = text,
        onValueChange = function,
        label = { Text(type) },
        placeholder = { Text("Enter your ${type}") },
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        leadingIcon = {
            Icon(
                Icons.Default.DriveFileRenameOutline,
                contentDescription = "",
                tint = Color.Gray
            )
        }, modifier = Modifier.padding(8.dp),

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )

    )
}