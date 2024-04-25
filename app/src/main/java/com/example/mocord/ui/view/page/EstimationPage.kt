package com.example.mocord.ui.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mocord.ui.view.container.PopUpView

@Composable
fun Estimation(popUp: PopUpView){
    Column {
        Text(text = "Estimation",
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxSize()
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button 1")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button 2")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button 3")
            }
        }
    }
}