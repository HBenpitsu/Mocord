package com.example.mocord.ui.view.panel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mocord.ui.viewModel.panel.LabelViewModel

@Composable
fun LabelPanel(labelModel: LabelViewModel) {
    Text(text = labelModel.label)
}