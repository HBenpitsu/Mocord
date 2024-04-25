package com.example.mocord.ui.viewModel.container

import com.example.mocord.domain.Panel
import com.example.mocord.domain.PanelList
import com.example.mocord.ui.viewModel.panel.PanelViewModel

class PanelListViewModel(
    private val panelList: PanelList = PanelList()
) {
    val existingPanelViewModels: MutableMap<Int, PanelViewModel> = mutableMapOf()

    val size: Int
        get() = panelList.size

    init {
        panelList.loadState()
    }

    fun getPanelViewModel(index: Int): PanelViewModel? {
        val panel = panelList[index]

        return if (existingPanelViewModels.keys.contains(panel.hashCode())) {
            existingPanelViewModels[panel.hashCode()]
        } else {
            val viewModel = PanelViewModel(panel)
            existingPanelViewModels[panel.hashCode()] = viewModel
            viewModel
        }
    }

    fun getPanel(index: Int): Panel{
        return panelList[index]
    }
}