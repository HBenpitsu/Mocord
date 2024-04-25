package com.example.mocord.ui.view.fragment

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DragHandle(target: MutableIntState, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .pointerInput(Unit){
                detectVerticalDragGestures { _, dragAmount ->
                    target.intValue += dragAmount.toInt()
                }
            }
    ) {
        Text(text = "Handle")
    }
}