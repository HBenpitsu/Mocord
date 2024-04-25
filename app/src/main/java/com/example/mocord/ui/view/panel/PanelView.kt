package com.example.mocord.ui.view.panel

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mocord.ui.view.TaskObserver
import com.example.mocord.ui.viewModel.panel.LabelViewModel
import com.example.mocord.ui.viewModel.panel.PanelViewModel

open class PanelView(val model: PanelViewModel, private val taskObserver: TaskObserver) {
    private var _offset: MutableIntState = mutableIntStateOf(0)
    private var _height: MutableIntState = mutableIntStateOf(0)
    var destination: Int = 0
    val offset: Int
        get() = _offset.intValue
    val height: Int
        get() = _height.intValue
    var onDrag: (panel: PanelView) -> Unit = {_->/*dummy*/}
    var onDragEnd: (panel: PanelView) -> Unit = {_->/*dummy*/}
    var onDragStart: (panel: PanelView) -> Unit = {_->/*dummy*/}
    var onMotionFinished: (panel: PanelView) -> Unit = {_->/*dummy*/}

    @Composable
    fun Content(){
        /* make each part that is unique to each models.*/
        val content = @Composable {when(model){
            is LabelViewModel -> {
                Column {
                    LabelPanel(model)
                    Text(text = "$model")
                }
            }
            else -> {
                Column {
                    Text(text = "Unknown Panel")
                    Text(text = "$model")
                }
            }
        }}
    }

    @Composable
    fun Construct(){
        _offset = remember {mutableIntStateOf(0)}
        _height = remember {mutableIntStateOf(0)}

        val animatedOffset = animateIntAsState(
            targetValue = _offset.intValue,
            label = "panel offset",
            finishedListener = {_->
                taskObserver.done(this, 0)
                onMotionFinished(this)
            }
        )

        val thisPanel = this

        Surface(
            color = Color.Black,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    _height.intValue = coordinates.size.height
                }
                .absoluteOffset {
                    IntOffset(0, animatedOffset.value)
                }
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
        ) {
            Row {
                Surface(
                    modifier = Modifier
                        .pointerInput(thisPanel.model.hashCode()) {
                            detectVerticalDragGestures(
                                onDragStart = {
                                    onDragStart(thisPanel)
                                },
                                onDragEnd = {
                                    settleDown()
                                    onDragEnd(thisPanel)
                                }
                            ) { change, dragAmount ->
                                change.consume()

                                _offset.intValue += dragAmount.toInt()
                                // DRAG_SCALER is to make the dragging feel right.
                                // If there's no adjustment, the dragging behavior will be too fast.
                                // Please change this logic if you find any better way.

                                onDrag(thisPanel)
                            }
                        }
                        .fillMaxHeight()
                ) {
                    Text(text = "Handle")
                }
                Column {
                    Content()
                    Text(text = "$this")
                    Text(text = "$thisPanel")
                }
            }
        }
    }

    fun isDraggedUpward(): Boolean {
        return _offset.intValue < 0
    }

    fun isDraggedDownward(): Boolean {
        return _offset.intValue > 0
    }

    fun settleDown() {
        _offset.intValue = destination
    }

    fun moveTo(destination: Int) {
        this.destination = destination
        settleDown()
    }

    val draggedDirection: Int
        get() = if (isDraggedUpward()) -1 else if (isDraggedDownward()) 1 else 0
}