package com.example.mocord.ui.viewModel.panel

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import com.example.mocord.domain.Panel
import com.example.mocord.ui.theme.PanelStyle

open class PanelViewModel(
    var panel: Panel,
    val style: PanelStyle = PanelStyle.DEFAULT
) {
    var offset: MutableIntState = mutableIntStateOf(0)
    var composed: Boolean = false
    var height: MutableIntState? = null
}