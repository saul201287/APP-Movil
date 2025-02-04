package com.saul223655.activitistates.ui.activitis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//@Preview(showsBackground = true)
@Composable
    fun MyView() {
    var number by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ContainerTop(Modifier.weight(1f)){number++}
        ContainerCenter(Modifier.weight(1f), onClick = {number++})
        ContainerBottom(number, Modifier.weight(1f))
    }
}
@Composable
fun ContainerTop(modifier: Modifier, onClick : ()->Unit){
    Box(
        modifier.background(Color.Blue),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {onClick()},
            modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp)
        ) { }
    }

}
@Composable
fun ContainerCenter(modifier: Modifier, onClick : ()->Unit){
    Box(
        modifier.background(Color.Blue),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {onClick()},
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp)
        ) { }
    }

}
@Composable
fun ContainerBottom(count: Int, modifier: Modifier){


}
