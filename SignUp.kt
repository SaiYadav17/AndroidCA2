package com.example.mylearning

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {

    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }
    var isDarkMode by remember { mutableStateOf(false) }

    val backgroundColor = if (isDarkMode) Color.Black else Color.White

    val isUsernameValid = username.length >= 4
    val isPasswordValid = password.length >= 6
    val isFormValid = isUsernameValid && isPasswordValid && userType.isNotEmpty()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Sign Up", fontSize = 24.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(40.dp))

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier.width(280.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            Text("Username")

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Jhon_doe") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Password")

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("********") }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text("User Type")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = userType == "Student",
                        onClick = { userType = "Student" }
                    )
                    Text("Student")
                }

                Spacer(modifier = Modifier.width(90.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = userType == "Admin",
                        onClick = { userType = "Admin" }
                    )
                    Text("Admin")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Mode")
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Signed up Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("SignUpData", "Username: $username")
                    Log.d("SignUpData", "Password: $password")
                    Log.d("SignUpData", "UserType: $userType")
                    Log.d("SignUpData", "DarkMode: $isDarkMode")

                    val intent = Intent(context, SuccessActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
        }
    }
}
