package com.example.mocord.ui.viewModel.container

import com.example.mocord.domain.PanelList
import com.example.mocord.ui.viewModel.panel.PanelViewModel

class PanelListViewModel(
    val panelList: PanelList = PanelList()
) {
    init {
        panelList.loadState()
    }

    val size: Int
        get() = panelList.size

    fun getPanel(position: Int): PanelViewModel {
        return panelList[position]
    }

    fun addPanel(position:Int ,panel: PanelViewModel) {
        panelList.add(position, panel)
        onPanelAddedHandler(position)
    }

    fun removePanel(position: Int) {
        panelList.removeAt(position)
        onPanelRemovedHandler(position)
    }

    fun swapPanel(from: Int, to: Int) {
        val temp = panelList[from]
        panelList[from] = panelList[to]
        panelList[to] = temp
        onPanelSwappedHandler(from, to)
    }
}