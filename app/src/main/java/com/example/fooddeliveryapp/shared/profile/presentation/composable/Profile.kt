package com.example.fooddeliveryapp.shared.profile.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Profile(){
    Column(
        modifier = Modifier
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        UserInfoSection()
        OrderHistoryButton()
        AddressManagementButton()
        Favorites()
        ChangePasswordButton()
        DeleteAccount()
        LogoutButton()
    }

}

@Composable
fun UserInfoSection() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
    ) {Row(){

        Column(modifier = Modifier.padding(24.dp)) {
            BasicText(text = "User Name: John Doe")
            BasicText(text = "Email: johndoe@example.com")
            BasicText(text = "Phone: +1234567690")
        }
    }

    }
}

@Composable
fun ChangePasswordButton() {
    Button(
        onClick = { /* Navigate to Change Password Screen */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text("Change Password")
    }
}

@Composable
fun OrderHistoryButton() {
    Button(
        onClick = { /* Navigate to Order History Screen */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text("Order History")
    }
}

@Composable
fun AddressManagementButton() {
    Button(
        onClick = { /* Navigate to Address Management Screen */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text("Address Management")
    }
}

@Composable
fun Favorites() {
    Button(
        onClick = { /* Navigate to Address Management Screen */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text("Favorites")
    }
}
@Composable
fun DeleteAccount() {
    Button(
        onClick = { /* Navigate to Address Management Screen */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text("Delete Your Account")
    }
}

@Composable
fun LogoutButton() {
    Button(
        onClick = { /* Handle Logout Logic */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Text("Logout",color = Color.LightGray)
    }
}