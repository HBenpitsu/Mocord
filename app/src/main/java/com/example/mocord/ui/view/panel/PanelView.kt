package com.example.mocord.ui.view.panel

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mocord.ui.view.fragment.DragHandle
import com.example.mocord.ui.viewModel.panel.PanelViewModel

@Composable
fun PanelView(model: PanelViewModel){
    val animatedIntOffset = animateIntAsState(
        targetValue = model.offset.intValue,
        label = "animatedIntOffset"
    )
    Box(
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                model.height = mutableIntStateOf(layoutCoordinates.size.height)
            }
            .absoluteOffset {
                IntOffset(0, animatedIntOffset.value)
            }
    ){
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row {
            DragHandle(target = model.offset, modifier = Modifier.fillMaxHeight())
            Column {
                when(model){
                    is LabelViewModel -> LabelPanel(model)
                    else -> Text(text = "Not implemented Yet")
                }
                Text(text = "$model")
                Text(text = "${model.offset.intValue}")
            }
        }
    }
    }
}