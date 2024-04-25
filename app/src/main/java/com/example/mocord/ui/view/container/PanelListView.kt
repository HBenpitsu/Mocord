package com.example.mocord.ui.view.container

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mocord.ui.view.TaskObserver
import com.example.mocord.ui.view.panel.PanelView
import com.example.mocord.ui.viewModel.container.PanelListViewModel
import com.example.mocord.ui.viewModel.panel.PanelViewModel

@Composable
fun PanelListView(modifier: Modifier = Modifier){
    val model = remember{ PanelListViewModel() }
    val listState = rememberLazyListState()

    LazyColumn (
        modifier = modifier,
        state = listState
    ) {
        items(model.size, key={index->index})itemContent@{index->
            val panelViewModel = model.getPanelViewModel(index) ?: return@itemContent
            PanelView(panelViewModel)
        }

    }
}