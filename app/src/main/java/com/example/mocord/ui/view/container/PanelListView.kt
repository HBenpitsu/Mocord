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

/* !TODO: make class into a function.
     what I cannot with a function is NOT to be implemented in this view layer!! */

@Composable
fun PanelListView(model: PanelListViewModel, modifier: Modifier = Modifier){

    val lastSwap = remember {mutableStateOf<Pair<Int, Int>?>(null)}
    val lazyListState = rememberLazyListState()
    val modelSize = remember {mutableIntStateOf(model.size)}

    val animationTaskObserver: TaskObserver = TaskObserver()
    val displayedPanelsState: DisplayedPanelsState = DisplayedPanelsState()

//        val visibleItemInfoIsNotEmpty = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() } }
//        val visibleItemInfoFirstIndex = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.first().index } }
//        val visibleItemInfoLastIndex = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.last().index } }
//        val purgingMargin = 1
//
//        if (visibleItemInfoIsNotEmpty.value) {
//            println("purging our of ${visibleItemInfoFirstIndex.value}..${visibleItemInfoLastIndex.value}")
//            displayedPanelsState.purgeViewsOutOf(
//                visibleItemInfoFirstIndex.value - purgingMargin
//                    ..visibleItemInfoLastIndex.value + purgingMargin
//            )
//        }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState
    ) {
        items(
            modelSize.intValue,
            key = {index -> model.panelList[index].hashCode()}
        ) {index ->
            val panelView = PanelView(model.panelList[index], animationTaskObserver)
            panelView.Construct()
            Text(text = "${displayedPanelsState.takeIdOf(index) ?: index}")
            displayedPanelsState.add(index, panelView)

        }
    }
}

class PanelListView(private val model: PanelListViewModel) {

    private val swapMargin: Int = 5
    /* When the panel cross a point displaced by `swapMargin` from the middle between two panels' center, swapping occurs.
    * This means that a band whose width is 2*`swapMargin` is created in the middle between two panels.
    * And when the drag mount exceeds this band, swapping occurs.
    * */

    private var lastSwap: Pair<Int, Int>? = null
    private val animationTaskObserver: TaskObserver = TaskObserver()
    private var modelSize: MutableIntState = mutableIntStateOf(model.size)
    private val displayedPanelsState: DisplayedPanelsState = DisplayedPanelsState()

    init {
        animationTaskObserver.onLastTaskDoneHandler = {
            lastSwap = null
        }
        model.onPanelAddedHandler = { _ ->
        }
//        model.onPanelSwappedHandler = { _, _ ->
//        }
    }

    fun addPanel(at: Int, panel: PanelViewModel){
        model.addPanel(at, panel)
        modelSize.intValue = model.size
    }
    fun removePanel(at: Int){}
    fun swapPanels(from: Int, to: Int) {
        model.swapPanel(from, to)
        modelSize.intValue = model.size
    }

    @Composable
    fun Construct(modifier: Modifier = Modifier){
        val lazyListState = rememberLazyListState()
        modelSize = remember {mutableIntStateOf(model.size)}

//        val visibleItemInfoIsNotEmpty = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() } }
//        val visibleItemInfoFirstIndex = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.first().index } }
//        val visibleItemInfoLastIndex = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.last().index } }
//        val purgingMargin = 1
//
//        if (visibleItemInfoIsNotEmpty.value) {
//            println("purging our of ${visibleItemInfoFirstIndex.value}..${visibleItemInfoLastIndex.value}")
//            displayedPanelsState.purgeViewsOutOf(
//                visibleItemInfoFirstIndex.value - purgingMargin
//                    ..visibleItemInfoLastIndex.value + purgingMargin
//            )
//        }

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = lazyListState
        ) {
            items(
                modelSize.intValue,
                key = {index -> model.panelList[index].hashCode()}
            ) {index ->
                val panelView = PanelView(model.panelList[index], animationTaskObserver)
                panelView.Construct()
                Text(text = "${displayedPanelsState.takeIdOf(index) ?: index}")
                displayedPanelsState.add(index, panelView)

            }
        }
    }
}

private class DisplayedPanelsState {
    private val indexPanelViewMap: MutableMap<Int, PanelView> = mutableMapOf()

    fun takeIdOf(index: Int): Int?{
        return indexPanelViewMap[index]?.hashCode()
    }

    fun purgeViewsOutOf(range: IntRange){
        indexPanelViewMap.filter { it.key !in range }.keys.forEach { indexPanelViewMap.remove(it) }
    }

    fun add(index: Int, panelView: PanelView){
        indexPanelViewMap[index] = panelView
    }

    fun swap(panelView1: PanelView, panelView2: PanelView){
        val index1 = getIndexOf(panelView1);val index2 = getIndexOf(panelView2)
        remove(panelView1);remove(panelView2)
        index1?.let { add(it, panelView2) };index2?.let { add(it, panelView1) }
    }

    fun remove(panelView: PanelView){
        getIndexOf(panelView)?.let { indexPanelViewMap.remove(it) }
    }

    fun getIndexOf(panelView: PanelView): Int?{
        return indexPanelViewMap.filter { it.value == panelView }.keys.firstOrNull()
    }

    fun getBeneathPanel(panelView: PanelView): PanelView?{
        val indexOfPanelView = getIndexOf(panelView) ?: return null
        return indexPanelViewMap[indexOfPanelView + 1]
    }

    fun getAbovePanel(panelView: PanelView): PanelView?{
        val indexOfPanelView = getIndexOf(panelView) ?: return null
        return indexPanelViewMap[indexOfPanelView - 1]
    }
}
