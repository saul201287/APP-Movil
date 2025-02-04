package com.saul223655.activitistates.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.menu.data.model.ProductCreateRequest
import com.saul223655.activitistates.menu.data.model.ProductDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(menuViewModel: MenuViewModel) {
    val name: String by menuViewModel.name.observeAsState("")
    val costo: Number by menuViewModel.costo.observeAsState(0)
    val cantidad: Number by menuViewModel.cantidad.observeAsState(0)
    val products: List<ProductDTO> by menuViewModel.products.observeAsState(emptyList())
    val success: Boolean by menuViewModel.success.observeAsState(false)
    val error: String by menuViewModel.error.observeAsState("")

    var showDialog by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        menuViewModel.getAll()
    }

   /* LaunchedEffect(success, error) {
        if (success && alertMessage.isEmpty()) {
            alertMessage = "Producto agregado exitosamente"
            isError = false
            showAlert = true
            menuViewModel.getAll()
            delay(5000)
            showAlert = false
        } else if (error.isNotEmpty()) {
            alertMessage = error
            isError = true
            showAlert = true
            delay(5000)
            showAlert = false
        }
    }
*/


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E88E5))
            .padding(16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { },
                title = {
                    Text(
                        text = if (isError) "Error" else "Éxito",
                        fontWeight = FontWeight.Bold,
                        color = if (isError) Color.Red else Color(0xFF4CAF50)
                    )
                },
                text = {
                    Text(
                        text = alertMessage,
                        fontSize = 16.sp
                    )
                },
                confirmButton = { },
                dismissButton = { }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
        ) {
            TableHeader()
            Spacer(modifier = Modifier.height(8.dp))
            TableContent(products)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Producto")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Agregar Producto", fontSize = 16.sp)
            }
        }

        if (showDialog) {
            AddProductDialog(
                onDismiss = { showDialog = false },
                onAddProduct = { name, cost, quantity ->
                    val request = ProductCreateRequest(name, cost.toInt(), quantity.toInt())
                    menuViewModel.viewModelScope.launch {
                        menuViewModel.onClick(request)
                        menuViewModel.getAll()
                        showDialog = false
                    }
                },
                name = name,
                cost = costo.toString(),
                quantity = cantidad.toString(),
                onNameChange = { menuViewModel.onChangeName(it) },
                onCostChange = {
                    if (it.isNotEmpty()) {
                        menuViewModel.onChangeCosto(it.toInt())
                    }
                },
                onQuantityChange = {
                    if (it.isNotEmpty()) {
                        menuViewModel.onChangeCantidad(it.toInt())
                    }
                }
            )
        }
    }
}

@Composable
fun AddProductDialog(
    onDismiss: () -> Unit,
    onAddProduct: (String, String, String) -> Unit,
    name: String,
    cost: String,
    quantity: String,
    onNameChange: (String) -> Unit,
    onCostChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Agregar Producto",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Nombre del producto:",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                BasicTextField(
                    value = name,
                    onValueChange = onNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Costo:",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                BasicTextField(
                    value = cost,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                            onCostChange(newValue)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cantidad:",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                BasicTextField(
                    value = quantity,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                            onQuantityChange(newValue)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onAddProduct(name, cost, quantity) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Agregar", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1565C0), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "ID",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Name",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Cantidad",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Precio",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TableContent(products: List<ProductDTO>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        products.forEachIndexed { index, product ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .background(if (index % 2 == 0) Color(0xFFF5F5F5) else Color.White)
                    .border(1.dp, Color(0xFFE0E0E0)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = product.id.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = product.cantidad.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$${product.costo}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            if (index < products.size - 1) {
                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
            }
        }
    }
}









