package com.saul223655.activitistates.register.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.login.presentation.AlertMessage
import kotlinx.coroutines.delay
import com.saul223655.activitistates.register.data.model.CreateUserRequest
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navigateToLogin: () -> Unit
) {
    val username: String by registerViewModel.username.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    val name: String by registerViewModel.name.observeAsState("")
    val lastname: String by registerViewModel.lastname.observeAsState("")
    val success: Boolean by registerViewModel.success.observeAsState(false)
    val error: String by registerViewModel.error.observeAsState("")

    var isPasswordVisible by remember { mutableStateOf(false) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() && lastname.isNotBlank() && username.isNotBlank() && password.isNotBlank()

    LaunchedEffect(success) {
        if (success) {
            showSuccessMessage = true
            delay(2000)
            showSuccessMessage = false
            navigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E88E5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = showSuccessMessage,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            AlertMessage(
                message = "Registro realizado con éxito",
                isError = false,
                onDismiss = { showSuccessMessage = false }
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            text = "Create Account",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            TextField(
                value = name,
                onValueChange = { registerViewModel.onChangeName(it) },
                label = { Text("Name", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("Enter your name", color = Color.LightGray) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Person Icon",
                        tint = Color(0xFF1E88E5)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = lastname,
                onValueChange = { registerViewModel.onChangeLastName(it) },
                label = { Text("Last Name", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("Enter your last name", color = Color.LightGray) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Person Icon",
                        tint = Color(0xFF1E88E5)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = username,
                onValueChange = { registerViewModel.onChangeUsername(it) },
                label = { Text("Username", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("Enter your username", color = Color.LightGray) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Person Icon",
                        tint = Color(0xFF1E88E5)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { registerViewModel.onChangePassword(it) },
                label = { Text("Password", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("Enter your password", color = Color.LightGray) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Lock Icon",
                        tint = Color(0xFF1E88E5)
                    )
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                            tint = Color(0xFF1E88E5)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (error.isNotBlank()) {
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val user = CreateUserRequest(username, password, name, lastname)
                    registerViewModel.viewModelScope.launch {
                        registerViewModel.onClick(user)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}