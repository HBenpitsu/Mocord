package com.example.mocord

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mocord.ui.theme.MocordTheme
import com.example.mocord.ui.view.container.PageHolder
import com.example.mocord.ui.view.container.PopUpView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val popUpView = PopUpView()

        setContent {
            MocordTheme {
                // A surface container using the 'background' color from the theme
                popUpView.Construct()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageHolder(popUpView)
                }
            }
        }
    }
}