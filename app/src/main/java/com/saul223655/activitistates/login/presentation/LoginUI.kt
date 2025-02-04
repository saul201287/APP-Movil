package com.saul223655.activitistates.login.presentation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.login.data.model.LoginUserRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AlertMessage(
    message: String,
    isError: Boolean,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isError) Color(0xFFFFEBEE) else Color(0xFFE8F5E9)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isError) Icons.Default.Error else Icons.Default.CheckCircle,
                    contentDescription = if (isError) "Error" else "Success",
                    tint = if (isError) Color(0xFFD32F2F) else Color(0xFF2E7D32)
                )
                Text(
                    text = message,
                    color = if (isError) Color(0xFFD32F2F) else Color(0xFF2E7D32)
                )
            }
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss",
                    tint = if (isError) Color(0xFFD32F2F) else Color(0xFF2E7D32)
                )
            }
        }
    }
}
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navigateToHome: () -> Unit,
    navigateToMenu: () -> Unit
) {
    val username: String by loginViewModel.username.observeAsState("")
    val password: String by loginViewModel.password.observeAsState("")
    val success: Boolean by loginViewModel.success.observeAsState(false)
    val error: String by loginViewModel.error.observeAsState("")
    var isPasswordVisible by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    val isFormValid = username.isNotBlank() && password.isNotBlank()

    LaunchedEffect(success) {
        if (success) {
            delay(1000)
            navigateToMenu()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCCC2DC)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = showAlert,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                AlertMessage(
                    message = if (success) "¡Inicio de sesión exitoso!" else error,
                    isError = !success,
                    onDismiss = { showAlert = false }
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Login",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = username,
                onValueChange = { loginViewModel.onChangeUsername(it)},
                label = { Text("Username") },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Username") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { loginViewModel.onChangePassword(it) },
                label = { Text("Password") },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon")},
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val user = LoginUserRequest(username, password)
                    loginViewModel.viewModelScope.launch {
                        loginViewModel.onClick(user)
                        showAlert = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = isFormValid
            ) {
                Text(
                    text = "Sign up",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navigateToHome() },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Registrar",
                    fontSize = 20.sp
                )
            }
        }
    }
}