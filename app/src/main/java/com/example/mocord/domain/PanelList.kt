package com.example.mocord.domain

/* currently this is a mock
* TODO: implement the actual PanelList
*  */

class PanelList {
    private val _panels = mutableListOf<Panel>()
    val size: Int
        get() = _panels.size

    init {}

    fun loadState() {
        // TODO: actually load the state of the panels
        _panels.clear()
        _panels.add(LabelPanel("label1"))
        _panels.add(LabelPanel("label2"))
        _panels.add(LabelPanel("label3"))
        _panels.add(LabelPanel("label4"))
        _panels.add(LabelPanel("label5"))
        _panels.add(LabelPanel("label6"))
        _panels.add(LabelPanel("label7"))
        _panels.add(LabelPanel("label8"))
    }

    fun saveState() {
        // TODO: save the state of the panels
    }

    var onSwap: ((from: Int, to: Int, panelFrom: Panel, panelTo: Panel) -> Unit)? = null
    fun swap(from: Int, to: Int) {
        val temp = _panels[from]
        _panels[from] = _panels[to]
        _panels[to] = temp
        onSwap?.invoke(from, to, _panels[from] ,_panels[to])
    }

    var onAdd: ((index: Int, panel: Panel) -> Unit)? = null
    fun insert(index: Int, panel: Panel) {
        _panels.add(index, panel)
        onAdd?.invoke(index, panel)
    }

    fun add(panel: Panel) {
        _panels.add(panel)
        onAdd?.invoke(_panels.size-1,panel)
    }

    var onRemove: ((index:Int, panel: Panel) -> Unit)? = null
    fun remove(panel: Panel) {
        val idx = _panels.indexOf(panel)
        _panels.remove(panel)
        onRemove?.invoke(idx,panel)
    }

    fun removeAt(index: Int) {
        onRemove?.invoke(index, _panels.removeAt(index))
    }

    operator fun get(index: Int): Panel {
        return _panels[index]
    }
}
