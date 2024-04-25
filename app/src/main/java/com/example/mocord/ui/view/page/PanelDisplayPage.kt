package com.example.mocord.ui.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mocord.ui.view.container.PanelListView
import com.example.mocord.ui.view.container.PopUpView
import com.example.mocord.ui.viewModel.container.PanelListViewModel
import com.example.mocord.ui.viewModel.panel.LabelViewModel
import com.example.mocord.ui.viewModel.panel.PanelViewModel

@Composable
fun PanelDisplay(popUp: PopUpView){
    val listModel = PanelListViewModel(
        mutableListOf<PanelViewModel>(
            LabelViewModel("Label 1", "Header 1"),
            LabelViewModel("Label 2", "Header 2"),
            LabelViewModel("Label 3", "Header 3"),
            LabelViewModel("Label 4", "Header 4"),
            LabelViewModel("Label 5", "Header 5"),
            LabelViewModel("Label 6", "Header 6"),
            LabelViewModel("Label 7", "Header 7"),
            LabelViewModel("Label 8", "Header 8")
        )
    )
    Column {

        PanelListView(
            listModel,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxSize()
        ) {
            Button(onClick = { popUp.show{} }) {
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