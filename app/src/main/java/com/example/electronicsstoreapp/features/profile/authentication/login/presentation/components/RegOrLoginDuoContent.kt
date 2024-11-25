package com.example.electronicsstoreapp.features.profile.authentication.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


@Composable
fun RegOrLoginDuo(onClick:() -> Unit, textString:String, buttonString:String){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(textString, fontSize = 14.sp, textAlign = TextAlign.Center)
        Button(
            onClick = onClick ,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colorScheme.surfaceBright
            ),
            elevation = null,
        ) {
            Text(
                text = buttonString,
                color = Color.hsl(0.1f,0.1f,0.5f),
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        }
    }
}